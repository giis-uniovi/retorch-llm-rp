package giis.retorch.llmresourceidentification;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ExperimentationHelper {

    static final String PATH_KEY = "CHATGPT_API_KEY";
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final String RESOURCES_ROUTE = "llm-rp-resourceidentification/src/main/resources";

    public ExperimentationHelper() {
        //This is the default constructor to avoid smells
    }

    public String getTestExamples(String testMethodName){

        List<String> listExamples = List.of("oneToOneChatInSessionChrome","oneToOneVideoAudioSessionChrome","forumRestOperations");

        if (listExamples.contains(testMethodName)){
            listExamples.remove(testMethodName);
            listExamples.add("forumRestOperations");
        }
        StringBuilder allContents = new StringBuilder();

        for (String fileName : listExamples) {
            String filePath = "llm-rp-resourceidentification/src/main/resources/input/annotated/" + fileName+".txt";
            try {
                String content = Files.readString(Paths.get(filePath));
                allContents.append(content).append("\n"); // Agrega salto de línea entre archivos
            } catch (IOException e) {
                log.error("Error leyendo el archivo: {}", filePath);

            }
        }
        return allContents.toString();


    }

    public String getTestCase(String testMethodName) {
        String filePath = "llm-rp-resourceidentification/src/main/resources/input/no-annotated/"+testMethodName+".txt";

        String testCase= loadFileIntoString(filePath);
        return testCase;
    }
    public void sendChatGPTRequest(String body, String model, String experimentname) throws IOException {

        String apiKey = System.getProperty(PATH_KEY) != null ? System.getProperty(PATH_KEY) : System.getenv(PATH_KEY);
        String urlString = "https://api.openai.com/v1/chat/completions";
        HttpURLConnection conn = null;
        try {
            // Create URL and HttpURLConnection objects
            URI uri = new URI(urlString);
            URL url = uri.toURL();
            conn = (HttpURLConnection) url.openConnection();

            // Set request method and headers
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + apiKey);
            conn.setDoOutput(true);

            // Create JSON request body
            JSONObject data = new JSONObject();
            data.put("model", model);

            // Create the message content
            JSONObject messageContent = new JSONObject();
            messageContent.put("type", "text");
            messageContent.put("text", body);

            // Create the user message
            JSONObject userMessage = new JSONObject();

            userMessage.put("role", "user");
            userMessage.put("content", new JSONArray().put(messageContent));

            // Add the user message to the messages array
            JSONArray messages = new JSONArray();
            messages.put(userMessage);

            // Create JSON request body
            data.put("messages", messages);
            data.put("temperature", 0.2); // More info at: https://community.openai.com/t/cheat-sheet-mastering-temperature-and-top-p-in-chatgpt-api/172683
            data.put("max_tokens", 4000); // Upper limit for the anwer.
            data.put("top_p", 1);
            data.put("frequency_penalty", 0);
            data.put("presence_penalty", 0);

            // Write JSON data to output stream
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = data.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Get response
            int responseCode = conn.getResponseCode();
            log.debug("Response Code:{} ", responseCode);

            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                String responseBody = scanner.useDelimiter("\\A").next();
                log.debug("Response Body: {}", responseBody);
                LocalDateTime now = LocalDateTime.now();

                // Define the desired format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-");

                // Format the current date and time
                String formattedDateTime = now.format(formatter);

                putOutputToFile(RESOURCES_ROUTE + "/outputs", experimentname + "-" + model + "-" + formattedDateTime, responseBody);
                conn.disconnect();
            }

        } catch (IOException | URISyntaxException e) {

            log.error(e.getMessage());

            if (conn != null) {

                log.error(conn.getErrorStream().toString());
                log.error("The response was {}", getErrorResponse(conn.getErrorStream()));
                conn.disconnect();
            }
            System.exit(-1);
        }

    }

    public  String loadFileIntoString(String path){
        String content="";
        try {
            content = Files.readString(Paths.get(path));
        } catch (IOException e) {
            log.error("Error reading the file: {} " , e.getMessage());
        }
        return content;
    }

    public void putOutputToFile(String filePath, String namePrompt, String output) throws IOException {
        log.debug("Creating the tmp directory to store methods output");
        File dir = new File(filePath);
        if (dir.exists()) {
            log.debug("The directory already exists: {}", dir.getAbsolutePath());
        } else {
            try {
                boolean isCreated = dir.mkdirs();
                if (isCreated) {
                    log.debug("Directory successfully created: {}", dir.getAbsolutePath());
                } else {
                    log.warn("Not able to create the directory: {}", dir.getAbsolutePath());
                }
            } catch (Exception e) {
                log.error("Something wrong happened creating the directory {} the exception stacktrace is:\n {}",
                        dir.getAbsolutePath(), e.getStackTrace());
            }
        }
        try (FileOutputStream outputStream = new FileOutputStream(filePath + "/" + namePrompt + ".txt")) {
            byte[] strToBytes = output.getBytes();
            outputStream.write(strToBytes);
        }
    }

    public String getErrorResponse(InputStream i) throws IOException {
        StringBuilder builder = new StringBuilder();
        InputStreamReader in = new InputStreamReader(i);
        BufferedReader br = new BufferedReader(in);
        String output;
        while ((output = br.readLine()) != null) {
            builder.append(output);
        }

        return builder.toString();
    }

    public String getTestCasesCrossValidation(int pos) throws IOException {
        String[] rawTestCases = openFileLoadContent(RESOURCES_ROUTE+"/input/inputSystemTestCases.txt").split("//TC");
        List<String> contentList = new ArrayList<>(Arrays.asList(rawTestCases));
        contentList.remove(pos);

        return String.join("", contentList);
    }

    public String openFileLoadContent(String route) throws IOException {
        String content;
        content = FileUtils.readFileToString(new File(route), StandardCharsets.UTF_8).replace("\r\n", "\n");

        return content;
    }

    public String getTestScenarios() throws IOException {
        return openFileLoadContent(RESOURCES_ROUTE + "/input/inputTestScenarios.txt");
    }

    public String getUserRequirements() throws IOException {
        return openFileLoadContent(RESOURCES_ROUTE + "/input/inputUserRequirements_en.txt");
    }

    public String getTestScenarioExample() throws IOException {
        return openFileLoadContent(RESOURCES_ROUTE + "/input/inputTestScenarioExample.txt");
    }

}



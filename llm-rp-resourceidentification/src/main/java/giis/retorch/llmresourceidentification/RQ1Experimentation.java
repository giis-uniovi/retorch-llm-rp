package giis.retorch.llmresourceidentification;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RQ1Experimentation {
    private static final Logger log = LoggerFactory.getLogger(RQ1Experimentation.class);
    private static final String OUT_BASE_PATH = "llm-rp-resourceidentification/target/prompts-input";
    static ExperimentationHelper exHelper;

    public static void main(String[] args) throws IOException {
        exHelper = new ExperimentationHelper();

        //= promptTestScenariosFewShot(exHelper.getUserRequirements(), exHelper.getTestScenarioExample());
        //exHelper.putOutputToFile(getOutBasePath(), "few-shot-prompt", prompt);
        //exHelper.sendChatGPTRequest(prompt, "gpt-4o-2024-05-13", "RQ1-few-shot-generaTestScenarios");
        //exHelper.sendChatGPTRequest(prompt, "gpt-4o-mini-2024-07-18", "RQ1-few-shot-generateTestScenarios");

        //log.debug("The prompt for FewShot is: {}", prompt);

        String jsonResource = exHelper.loadFileIntoString("llm-rp-resourceidentification/src/main/resources/RETORCHFullTeachingResources.json");
        String testExamples = exHelper.loadFileIntoString("llm-rp-resourceidentification/src/main/resources/input/E2EFullteachingTestsExamples.txt");
        String testMethod = "";
        for (int i = 1; i <= 9; i++) {
            testMethod=exHelper.loadFileIntoString("llm-rp-resourceidentification/src/main/resources/input/TC" + i + ".txt");

            String prompt = promptIdentifyResourcesFewShotCoT(jsonResource, testExamples, testMethod);
            log.debug("The prompt for Few Shot with CoT is: {}", prompt);
            exHelper.putOutputToFile(getOutBasePath(), "few-shot-CoT-prompt"+i, prompt);
        }


        //exHelper.sendChatGPTRequest(prompt, "gpt-4o-mini-2024-07-18", "RQ1-few-shot-cot-generateTestScenarios");
        //exHelper.sendChatGPTRequest(prompt, "gpt-4o-2024-05-13", "RQ1-few-shot-cot-generateTestScenarios");
    }


    public static String getOutBasePath() {
        return OUT_BASE_PATH;
    }


    public static String promptIdentifyResourcesFewShotCoT(String resourcesJsonFile, String testExamples, String testtoIdentify) {
        return "You are an expert software tester who specializes in system testing. Your task is to identify the test " +
                "Resources used in system test cases and annotate them with \\@AccessMode.\n " +
                "The available test Resources are in the following JSON file:\n \"\"\"" + resourcesJsonFile + "\"\"\"\n" +
                " each resource is annotated with an \\@AccessMode annotation.\n" +
                "The goal is to identify the different Resources and focal methods, which are methods that are reachable " +
                "from the test prefix and are most likely to interact with a given Resource. The generated access modes " +
                "must only refer to the Resources defined in the JSON file.\n" +
                "Here, there are examples of methods already identified:\n \"\"\"" + testExamples + "\"\"\" \n" +
                "Identify the Resources of the following test method:\n\"\"\"" + testtoIdentify + "\"\"\" \n" +
                "First get the explanation about why the Resources are required, and then generate the @AccessMode annotations.";
    }

}
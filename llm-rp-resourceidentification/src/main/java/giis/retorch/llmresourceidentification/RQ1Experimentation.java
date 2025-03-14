package giis.retorch.llmresourceidentification;

import giis.retorch.llmrp.ExperimentationHelper;
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

        String jsonResource= exHelper.loadFileIntoString("llm-rp-resourceidentification/src/main/resources/RETORCHFullTeachingResources.json");
        String testExamples= exHelper.loadFileIntoString("llm-rp-resourceidentification/src/main/resources/input/E2EFullteachingTestsExamples.txt");
        String testMethod= exHelper.loadFileIntoString("llm-rp-resourceidentification/src/main/resources/input/TC1.txt");


        String prompt = promptIdentifyResourcesFewShotCoT(jsonResource, testExamples,testMethod);
        log.debug("The prompt for Few Shot with CoT is: {}", prompt);
        exHelper.putOutputToFile(getOutBasePath(), "few-shot-CoT-prompt", prompt);
        //exHelper.sendChatGPTRequest(prompt, "gpt-4o-mini-2024-07-18", "RQ1-few-shot-cot-generateTestScenarios");
        //exHelper.sendChatGPTRequest(prompt, "gpt-4o-2024-05-13", "RQ1-few-shot-cot-generateTestScenarios");
    }


    public static String getOutBasePath() {
        return OUT_BASE_PATH;
    }


    public static String promptIdentifyResourcesFewShotCoT(String resourcesJsonFile, String testExamples,String testtoIdentify) {
        return "Let’s think step by step, You're a expert tester able to identify the test Resources and how are used in the System Test cases. The " +
                "test Resources available in the system are in the following JSON file:\n \"\"\"" + resourcesJsonFile+"\"\"\"\n"+
                "the Resources are annotated with a @AccessMode annotation. Let's Identify the different Resources and focal methods: a method or set of methods that are" +
                "reachable from the test preﬁx, that is more likely to use the Resource.\n " +
                "Here there are examples of methods already identified:\n \"\"\"" +testExamples+"\"\"\" \n"+
                "Identify the Resources of the following test method:\n\"\"\"" +testtoIdentify+"\"\"\" \n" +
                "First get the explanation about why the Resources are required, and then generate the @AccessMode annotations.";
    }

}
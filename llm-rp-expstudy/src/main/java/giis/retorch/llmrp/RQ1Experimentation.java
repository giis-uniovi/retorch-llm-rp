package giis.retorch.llmrp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RQ1Experimentation {
    private static final Logger log = LoggerFactory.getLogger(RQ1Experimentation.class);
    private static final String OUT_BASE_PATH = "llm-rp-expstudy/target/prompts-input/RQ1";
    static ExperimentationHelper exHelper;

    public static void main(String[] args) throws IOException {
        exHelper = new ExperimentationHelper();
        String prompt = promptTestScenariosFewShot(exHelper.getUserRequirements(), exHelper.getTestScenarioExample());
        exHelper.putOutputToFile(getOutBasePath(), "few-shot-prompt", prompt);
        exHelper.sendChatGPTRequest(prompt, "gpt-4o-2024-05-13", "RQ1-few-shot-generaTestScenarios");
        exHelper.sendChatGPTRequest(prompt, "gpt-4o-mini-2024-07-18", "RQ1-few-shot-generateTestScenarios");

        log.debug("The prompt for FewShot is: {}", prompt);

        prompt = promptTestScenariosFewShotCoT(exHelper.getUserRequirements(), exHelper.getTestScenarioExample());
        log.debug("The prompt for Few Shot with CoT is: {}", prompt);
        exHelper.putOutputToFile(getOutBasePath(), "few-shot-CoT-prompt", prompt);
        exHelper.sendChatGPTRequest(prompt, "gpt-4o-mini-2024-07-18", "RQ1-few-shot-cot-generateTestScenarios");
        exHelper.sendChatGPTRequest(prompt, "gpt-4o-2024-05-13", "RQ1-few-shot-cot-generateTestScenarios");
    }

    static String promptTestScenariosFewShot(String userRequirements, String testScenariosExamples) {
        return "I would like to generate test scenarios for system testing\n" + "I know that I need to fulfill the user requirements:\n \"\"\" " + userRequirements + "\"\"\",\n " + "Provide a complete sequence of steps for each scenario and the expected outputs.\n" + "Fill in any missing steps\n" + "Identify any unnecessary steps.\n" + "Examples of a test scenario: \n \"\"\" " + testScenariosExamples + " \"\"\" \n";
    }

    public static String getOutBasePath() {
        return OUT_BASE_PATH;
    }

    static String promptTestScenariosFewShotCoT(String userRequirements, String testScenariosExamples) {
        return "Let’s think step by step, describe the solution and remark which user requirements are covered \n" + promptTestScenariosFewShot(userRequirements, testScenariosExamples);
    }

}
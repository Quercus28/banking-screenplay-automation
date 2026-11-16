package co.edu.udea.certificacion.banking.demo;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCase;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestStepStarted;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

/** Cucumber plugin that tells {@link DemoOverlay} which scenario and Gherkin step are running. */
public class DemoPlugin implements ConcurrentEventListener {

    private int stepNumber;
    private int totalSteps;
    private String lastType = "Given";

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::scenarioStarted);
        publisher.registerHandlerFor(TestStepStarted.class, this::stepStarted);
        publisher.registerHandlerFor(TestCaseFinished.class, this::scenarioFinished);
    }

    private void scenarioStarted(TestCaseStarted event) {
        TestCase scenario = event.getTestCase();
        if (!DemoOverlay.isEnabled()) {
            DemoOverlay.stop();
            return;
        }
        stepNumber = 0;
        totalSteps = (int) scenario.getTestSteps().stream().filter(PickleStepTestStep.class::isInstance).count();
        lastType = "Given";
        DemoOverlay.startScenario(featureName(scenario.getUri()), scenario.getName());
    }

    private void stepStarted(TestStepStarted event) {
        if (!DemoOverlay.isActive() || !(event.getTestStep() instanceof PickleStepTestStep)) {
            return;
        }
        PickleStepTestStep step = (PickleStepTestStep) event.getTestStep();
        String keyword = step.getStep().getKeyword().trim();
        if (!"And".equals(keyword) && !"But".equals(keyword)) {
            lastType = keyword;
        }
        DemoOverlay.gherkinStep(++stepNumber, totalSteps, keyword, lastType, step.getStep().getText());
    }

    private void scenarioFinished(TestCaseFinished event) {
        DemoOverlay.finishScenario(event.getResult().getStatus() == Status.PASSED);
    }

    private static String featureName(URI uri) {
        try {
            return Files.readAllLines(Paths.get(uri)).stream()
                    .map(String::trim)
                    .filter(line -> line.startsWith("Feature:"))
                    .map(line -> line.substring("Feature:".length()).trim())
                    .findFirst()
                    .orElse("");
        } catch (IOException | RuntimeException couldNotRead) {
            return "";
        }
    }
}

package co.edu.udea.certificacion.banking.demo;

import net.thucydides.model.screenshots.ScreenshotAndHtmlSource;
import net.thucydides.model.steps.ExecutedStepDescription;
import net.thucydides.model.steps.StepFailure;
import net.thucydides.model.steps.StepListenerAdapter;

import java.time.ZonedDateTime;
import java.util.List;

/** Serenity listener that tells {@link DemoOverlay} which Screenplay task or interaction is running. */
public class DemoStepListener extends StepListenerAdapter {

    @Override
    public void stepStarted(ExecutedStepDescription description) {
        DemoOverlay.screenplayStepStarted(description.getTitle());
    }

    @Override
    public void stepFinished() {
        DemoOverlay.screenplayStepFinished();
    }

    @Override
    public void stepFinished(List<ScreenshotAndHtmlSource> screenshots, ZonedDateTime time) {
        DemoOverlay.screenplayStepFinished();
    }

    @Override
    public void stepFailed(StepFailure failure) {
        DemoOverlay.screenplayStepFinished();
    }

    @Override
    public void stepFailed(StepFailure failure, List<ScreenshotAndHtmlSource> screenshots, boolean takeScreenshots,
                           ZonedDateTime time) {
        DemoOverlay.screenplayStepFinished();
    }

    @Override
    public void stepIgnored() {
        DemoOverlay.screenplayStepFinished();
    }

    @Override
    public void stepPending() {
        DemoOverlay.screenplayStepFinished();
    }
}

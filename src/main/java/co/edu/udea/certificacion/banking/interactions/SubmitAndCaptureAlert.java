package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Clicks a submit button with a direct WebDriver click and captures the alert it may open.
 * The direct click prevents any action after the click from running into the open alert.
 */
public class SubmitAndCaptureAlert implements Interaction {

    private final Target button;

    public SubmitAndCaptureAlert(Target button) {
        this.button = button;
    }

    public static SubmitAndCaptureAlert with(Target button) {
        return instrumented(SubmitAndCaptureAlert.class, button);
    }

    @Override
    @Step("{0} clicks #button")
    public <T extends Actor> void performAs(T actor) {
        button.resolveFor(actor).getWrappedElement().click();
        actor.attemptsTo(CaptureAlert.ifPresent());
    }
}

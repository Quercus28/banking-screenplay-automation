package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static co.edu.udea.certificacion.banking.utils.Constants.LAST_ALERT;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Waits briefly for a native alert (window.alert), remembers its text and accepts it.
 * If no alert shows up, the actor remembers an empty text.
 */
public class CaptureAlert implements Interaction {

    private static final Duration TIMEOUT = Duration.ofSeconds(2);

    public static CaptureAlert ifPresent() {
        return instrumented(CaptureAlert.class);
    }

    @Override
    @Step("{0} checks whether an alert is displayed and accepts it")
    public <T extends Actor> void performAs(T actor) {
        String text = "";
        try {
            Alert alert = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), TIMEOUT)
                    .until(ExpectedConditions.alertIsPresent());
            text = alert.getText();
            alert.accept();
        } catch (TimeoutException noAlert) {
            text = "";
        }
        actor.remember(LAST_ALERT, text);
    }
}

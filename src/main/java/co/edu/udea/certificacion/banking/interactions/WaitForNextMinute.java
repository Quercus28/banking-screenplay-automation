package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import java.time.LocalTime;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Waits until the first second of the next minute. The transaction filter works by minutes,
 * so it is used to place transactions in different minutes.
 */
public class WaitForNextMinute implements Interaction {

    public static WaitForNextMinute toStart() {
        return instrumented(WaitForNextMinute.class);
    }

    @Override
    @Step("{0} waits for the next minute")
    public <T extends Actor> void performAs(T actor) {
        long remainingSeconds = 60L - LocalTime.now().getSecond() + 1L;
        try {
            Thread.sleep(remainingSeconds * 1000L);
        } catch (InterruptedException interruption) {
            Thread.currentThread().interrupt();
        }
    }
}

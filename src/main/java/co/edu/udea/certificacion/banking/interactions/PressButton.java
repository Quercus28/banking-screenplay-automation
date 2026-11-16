package co.edu.udea.certificacion.banking.interactions;

import co.edu.udea.certificacion.banking.userinterfaces.BankPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Clicks the first visible button with the given text (Home, Logout, Deposit, Back, '>'...). */
public class PressButton implements Interaction {

    private final String text;

    public PressButton(String text) {
        this.text = text;
    }

    public static PressButton called(String text) {
        return instrumented(PressButton.class, text);
    }

    @Override
    @Step("{0} clicks the '#text' button")
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade button = BankPage.button(text).resolveAllFor(actor).stream()
                .filter(WebElementFacade::isCurrentlyVisible)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("There is no visible button with the text: " + text));
        button.click();
    }
}

package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.PressButton;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.button;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ViewTransactions implements Task {

    public static ViewTransactions ofTheAccount() {
        return instrumented(ViewTransactions.class);
    }

    @Override
    @Step("{0} views the account transactions")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                PressButton.called("Transactions"),
                WaitUntil.the(button("Back"), isVisible()).forNoMoreThan(10).seconds()
        );
    }
}

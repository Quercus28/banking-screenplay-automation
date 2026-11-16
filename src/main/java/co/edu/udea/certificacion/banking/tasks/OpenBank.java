package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.ResetBankData;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.button;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenBank implements Task {

    public static OpenBank withDemoData() {
        return instrumented(OpenBank.class);
    }

    @Override
    @Step("{0} opens XYZ Bank with the demo data")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ResetBankData.andOpen("#/login"),
                WaitUntil.the(button("Bank Manager Login"), isVisible()).forNoMoreThan(15).seconds()
        );
    }
}

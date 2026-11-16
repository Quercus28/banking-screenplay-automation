package co.edu.udea.certificacion.banking.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.ACCOUNTS;
import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.NUMBER;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;

public class SelectAccount implements Task {

    private final String number;

    public SelectAccount(String number) {
        this.number = number;
    }

    public static SelectAccount number(String number) {
        return instrumented(SelectAccount.class, number);
    }

    @Override
    @Step("{0} selects the account '#number'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(number).from(ACCOUNTS),
                WaitUntil.the(NUMBER, containsText(number)).forNoMoreThan(5).seconds()
        );
    }
}

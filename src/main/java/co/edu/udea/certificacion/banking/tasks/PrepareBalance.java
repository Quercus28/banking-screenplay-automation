package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.PressButton;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.BALANCE;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionForm.WITHDRAWAL_LABEL;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Leaves the newly opened account with the given balance by making a deposit. At the end it opens
 * the Withdrawl tab, so the next transaction starts with a clean form.
 */
public class PrepareBalance implements Task {

    private final String balance;

    public PrepareBalance(String balance) {
        this.balance = balance;
    }

    public static PrepareBalance of(int balance) {
        return instrumented(PrepareBalance.class, String.valueOf(balance));
    }

    @Override
    @Step("{0} leaves the account with a balance of #balance")
    public <T extends Actor> void performAs(T actor) {
        if ("0".equals(balance)) {
            return;
        }
        actor.attemptsTo(
                MakeTransaction.deposit(balance),
                WaitUntil.the(BALANCE, containsText(balance)).forNoMoreThan(5).seconds(),
                PressButton.called("Withdrawl"),
                WaitUntil.the(WITHDRAWAL_LABEL, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}

package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.EnterText;
import co.edu.udea.certificacion.banking.interactions.PressButton;
import co.edu.udea.certificacion.banking.questions.Visibility;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.TransactionForm.AMOUNT;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionForm.DEPOSIT_LABEL;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionForm.SUBMIT;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionForm.WITHDRAWAL_LABEL;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Deposits or withdraws an amount. If the form is already open it is reused, just like a user who
 * types a new amount without switching tabs. It waits for the form label before typing: if the
 * value is typed while the view is being replaced, it is lost (HAL-17).
 */
public class MakeTransaction implements Task {

    private final String type;
    private final String amount;

    public MakeTransaction(String type, String amount) {
        this.type = type;
        this.amount = amount;
    }

    public static MakeTransaction deposit(String amount) {
        return instrumented(MakeTransaction.class, "deposit", amount);
    }

    public static MakeTransaction withdrawal(String amount) {
        return instrumented(MakeTransaction.class, "withdrawal", amount);
    }

    @Override
    @Step("{0} makes a #type of '#amount'")
    public <T extends Actor> void performAs(T actor) {
        boolean isDeposit = "deposit".equals(type);
        Target label = isDeposit ? DEPOSIT_LABEL : WITHDRAWAL_LABEL;
        if (!actor.asksFor(Visibility.of(label))) {
            actor.attemptsTo(
                    PressButton.called(isDeposit ? "Deposit" : "Withdrawl"),
                    WaitUntil.the(label, isVisible()).forNoMoreThan(10).seconds()
            );
        }
        actor.attemptsTo(
                EnterText.theValue(amount, AMOUNT),
                Click.on(SUBMIT)
        );
    }
}

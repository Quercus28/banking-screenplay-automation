package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.PressButton;
import co.edu.udea.certificacion.banking.questions.Visibility;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.button;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LoginAsBankManager implements Task {

    public static LoginAsBankManager now() {
        return instrumented(LoginAsBankManager.class);
    }

    @Override
    @Step("{0} logs in as bank manager (Bank Manager Login)")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(Visibility.of(button("Bank Manager Login")))) {
            actor.attemptsTo(PressButton.called("Home"));
        }
        actor.attemptsTo(
                PressButton.called("Bank Manager Login"),
                WaitUntil.the(button("Customers"), isVisible()).forNoMoreThan(10).seconds()
        );
    }
}

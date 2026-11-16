package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.PressButton;
import co.edu.udea.certificacion.banking.questions.Visibility;
import co.edu.udea.certificacion.banking.userinterfaces.AddCustomerForm;
import co.edu.udea.certificacion.banking.userinterfaces.CustomersPage;
import co.edu.udea.certificacion.banking.userinterfaces.OpenAccountForm;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.button;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/** Opens a manager tab: Add Customer, Open Account or Customers. */
public class GoToManagerTab implements Task {

    private final String tab;

    public GoToManagerTab(String tab) {
        this.tab = tab;
    }

    public static GoToManagerTab called(String tab) {
        return instrumented(GoToManagerTab.class, tab);
    }

    @Override
    @Step("{0} opens the manager's '#tab' tab")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(Visibility.of(button(tab)))) {
            actor.attemptsTo(LoginAsBankManager.now());
        }
        actor.attemptsTo(
                PressButton.called(tab),
                WaitUntil.the(contentOf(tab), isVisible()).forNoMoreThan(10).seconds()
        );
    }

    private static Target contentOf(String tab) {
        switch (tab) {
            case "Add Customer":
                return AddCustomerForm.FIRST_NAME;
            case "Open Account":
                return OpenAccountForm.CURRENCY;
            case "Customers":
                return CustomersPage.SEARCH;
            default:
                throw new IllegalArgumentException("Unknown manager tab: " + tab);
        }
    }
}

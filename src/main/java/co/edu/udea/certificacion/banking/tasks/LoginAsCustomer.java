package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.PressButton;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.AccountPage.WELCOME;
import static co.edu.udea.certificacion.banking.userinterfaces.CustomerLoginPage.CUSTOMER;
import static co.edu.udea.certificacion.banking.userinterfaces.CustomerLoginPage.LOGIN;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LoginAsCustomer implements Task {

    private final String customer;

    public LoginAsCustomer(String customer) {
        this.customer = customer;
    }

    public static LoginAsCustomer named(String customer) {
        return instrumented(LoginAsCustomer.class, customer);
    }

    @Override
    @Step("{0} logs in as the customer '#customer'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenCustomerLogin.withoutSelectingAName(),
                SelectFromOptions.byVisibleText(customer).from(CUSTOMER),
                Click.on(LOGIN),
                WaitUntil.the(WELCOME, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    /** Takes the actor to the Customer Login view from any view. */
    public static class OpenCustomerLogin implements Task {

        public static OpenCustomerLogin withoutSelectingAName() {
            return instrumented(OpenCustomerLogin.class);
        }

        @Override
        @Step("{0} opens the customer login")
        public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    PressButton.called("Home"),
                    PressButton.called("Customer Login"),
                    WaitUntil.the(CUSTOMER, isVisible()).forNoMoreThan(10).seconds()
            );
        }
    }
}

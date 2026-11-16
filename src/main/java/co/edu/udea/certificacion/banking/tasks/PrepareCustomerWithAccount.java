package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.models.Customer;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Creates a customer with an account as the manager and logs in as that customer. */
public class PrepareCustomerWithAccount implements Task {

    private final String customer;
    private final String currency;

    public PrepareCustomerWithAccount(String customer, String currency) {
        this.customer = customer;
        this.currency = currency;
    }

    public static PrepareCustomerWithAccount in(String customer, String currency) {
        return instrumented(PrepareCustomerWithAccount.class, customer, currency);
    }

    @Override
    @Step("{0} prepares the customer '#customer' with an account in '#currency'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenBank.withDemoData(),
                RegisterCustomer.with(Customer.named(customer, "QA0001")),
                OpenAccount.in(currency, customer),
                LoginAsCustomer.named(customer)
        );
    }
}

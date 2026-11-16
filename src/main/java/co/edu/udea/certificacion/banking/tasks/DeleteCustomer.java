package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.models.Customer;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.edu.udea.certificacion.banking.userinterfaces.CustomersPage.deleteButton;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteCustomer implements Task {

    private final String fullName;

    public DeleteCustomer(String fullName) {
        this.fullName = fullName;
    }

    public static DeleteCustomer called(String fullName) {
        return instrumented(DeleteCustomer.class, fullName);
    }

    @Override
    @Step("{0} deletes the customer '#fullName'")
    public <T extends Actor> void performAs(T actor) {
        Customer customer = Customer.named(fullName, "");
        actor.attemptsTo(
                SearchCustomer.withoutFilter(),
                Click.on(deleteButton(customer.getFirstName(), customer.getLastName()))
        );
    }
}

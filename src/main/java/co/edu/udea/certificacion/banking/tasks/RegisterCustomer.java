package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.EnterText;
import co.edu.udea.certificacion.banking.interactions.SubmitAndCaptureAlert;
import co.edu.udea.certificacion.banking.models.Customer;
import co.edu.udea.certificacion.banking.questions.Visibility;
import co.edu.udea.certificacion.banking.utils.Memory;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.edu.udea.certificacion.banking.userinterfaces.AddCustomerForm.ADD_CUSTOMER;
import static co.edu.udea.certificacion.banking.userinterfaces.AddCustomerForm.FIRST_NAME;
import static co.edu.udea.certificacion.banking.userinterfaces.AddCustomerForm.LAST_NAME;
import static co.edu.udea.certificacion.banking.userinterfaces.AddCustomerForm.POST_CODE;
import static co.edu.udea.certificacion.banking.utils.Constants.CUSTOMER_CREATED_PREFIX;
import static co.edu.udea.certificacion.banking.utils.Constants.CUSTOMER_IDS;
import static co.edu.udea.certificacion.banking.utils.Constants.LAST_ALERT;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Fills in Add Customer, submits it and remembers the alert and the assigned id. */
public class RegisterCustomer implements Task {

    private final String firstName;
    private final String lastName;
    private final String postCode;

    public RegisterCustomer(String firstName, String lastName, String postCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
    }

    public static RegisterCustomer with(String firstName, String lastName, String postCode) {
        return instrumented(RegisterCustomer.class, orEmpty(firstName), orEmpty(lastName), orEmpty(postCode));
    }

    public static RegisterCustomer with(Customer customer) {
        return with(customer.getFirstName(), customer.getLastName(), customer.getPostCode());
    }

    private static String orEmpty(String value) {
        return value == null ? "" : value;
    }

    @Override
    @Step("{0} registers the customer '#firstName' '#lastName' with post code '#postCode'")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(Visibility.of(FIRST_NAME))) {
            actor.attemptsTo(GoToManagerTab.called("Add Customer"));
        }
        actor.attemptsTo(
                EnterText.theValue(firstName, FIRST_NAME),
                EnterText.theValue(lastName, LAST_NAME),
                EnterText.theValue(postCode, POST_CODE),
                SubmitAndCaptureAlert.with(ADD_CUSTOMER)
        );
        String alert = actor.recall(LAST_ALERT);
        if (alert.startsWith(CUSTOMER_CREATED_PREFIX)) {
            Memory.<Integer>list(actor, CUSTOMER_IDS)
                    .add(Integer.valueOf(alert.substring(CUSTOMER_CREATED_PREFIX.length()).trim()));
        }
    }
}

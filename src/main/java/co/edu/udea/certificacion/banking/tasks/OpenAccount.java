package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.SubmitAndCaptureAlert;
import co.edu.udea.certificacion.banking.questions.Visibility;
import co.edu.udea.certificacion.banking.utils.Memory;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import java.util.ArrayList;

import static co.edu.udea.certificacion.banking.userinterfaces.OpenAccountForm.CURRENCY;
import static co.edu.udea.certificacion.banking.userinterfaces.OpenAccountForm.CUSTOMER;
import static co.edu.udea.certificacion.banking.userinterfaces.OpenAccountForm.NO_CURRENCY;
import static co.edu.udea.certificacion.banking.userinterfaces.OpenAccountForm.NO_CUSTOMER;
import static co.edu.udea.certificacion.banking.userinterfaces.OpenAccountForm.PROCESS;
import static co.edu.udea.certificacion.banking.utils.Constants.ACCOUNT_CREATED_PREFIX;
import static co.edu.udea.certificacion.banking.utils.Constants.ACCOUNT_NUMBERS;
import static co.edu.udea.certificacion.banking.utils.Constants.LAST_ALERT;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Processes Open Account. An empty customer or currency explicitly selects the blank option,
 * because the form keeps the previous selection when the tab is opened again.
 */
public class OpenAccount implements Task {

    private final String currency;
    private final String customer;

    public OpenAccount(String currency, String customer) {
        this.currency = currency;
        this.customer = customer;
    }

    public static OpenAccount in(String currency, String customer) {
        return instrumented(OpenAccount.class, currency, customer);
    }

    @Override
    @Step("{0} opens an account in '#currency' for the customer '#customer'")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(Visibility.of(CURRENCY))) {
            actor.attemptsTo(GoToManagerTab.called("Open Account"));
        }
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(customer.isEmpty() ? NO_CUSTOMER : customer).from(CUSTOMER),
                SelectFromOptions.byVisibleText(currency.isEmpty() ? NO_CURRENCY : currency).from(CURRENCY),
                SubmitAndCaptureAlert.with(PROCESS)
        );
        String alert = actor.recall(LAST_ALERT);
        if (alert.startsWith(ACCOUNT_CREATED_PREFIX)) {
            String number = alert.substring(ACCOUNT_CREATED_PREFIX.length()).trim();
            Memory.<String>list(actor, ACCOUNT_NUMBERS).add(number);
            Memory.accountsByCustomer(actor).computeIfAbsent(customer, key -> new ArrayList<>()).add(number);
        }
    }
}

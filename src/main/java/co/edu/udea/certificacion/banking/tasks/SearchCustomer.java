package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.EnterText;
import co.edu.udea.certificacion.banking.questions.Visibility;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.edu.udea.certificacion.banking.userinterfaces.CustomersPage.SEARCH;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SearchCustomer implements Task {

    private final String text;

    public SearchCustomer(String text) {
        this.text = text;
    }

    public static SearchCustomer with(String text) {
        return instrumented(SearchCustomer.class, text);
    }

    public static SearchCustomer withoutFilter() {
        return with("");
    }

    @Override
    @Step("{0} searches for '#text' in the customer list")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(Visibility.of(SEARCH))) {
            actor.attemptsTo(GoToManagerTab.called("Customers"));
        }
        actor.attemptsTo(EnterText.theValue(text, SEARCH));
    }
}

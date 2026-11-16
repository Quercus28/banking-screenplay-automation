package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.EnterDateTime;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.edu.udea.certificacion.banking.userinterfaces.TransactionsPage.END_DATE;
import static co.edu.udea.certificacion.banking.userinterfaces.TransactionsPage.START_DATE;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Applies the history date filter. An empty value leaves that end of the range unchanged. */
public class FilterTransactions implements Task {

    private final String from;
    private final String to;

    public FilterTransactions(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public static FilterTransactions between(String from, String to) {
        return instrumented(FilterTransactions.class, from, to);
    }

    public static FilterTransactions until(String to) {
        return between("", to);
    }

    @Override
    @Step("{0} filters the transactions from '#from' to '#to'")
    public <T extends Actor> void performAs(T actor) {
        if (!from.isEmpty()) {
            actor.attemptsTo(EnterDateTime.with(from, START_DATE));
        }
        if (!to.isEmpty()) {
            actor.attemptsTo(EnterDateTime.with(to, END_DATE));
        }
    }
}

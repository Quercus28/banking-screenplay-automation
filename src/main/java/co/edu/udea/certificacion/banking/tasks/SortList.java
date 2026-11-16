package co.edu.udea.certificacion.banking.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.sortableHeader;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Clicks the header of a sortable column (customer list or transaction history). */
public class SortList implements Task {

    private final String column;

    public SortList(String column) {
        this.column = column;
    }

    public static SortList byColumn(String column) {
        return instrumented(SortList.class, column);
    }

    @Override
    @Step("{0} sorts the list by the '#column' column")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(sortableHeader(column)));
    }
}

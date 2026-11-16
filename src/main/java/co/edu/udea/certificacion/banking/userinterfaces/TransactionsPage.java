package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Transactions view of the account. */
public final class TransactionsPage {

    public static final Target START_DATE = Target.the("start date filter").locatedBy("#start");
    public static final Target END_DATE = Target.the("end date filter").locatedBy("#end");
    public static final Target ROWS = Target.the("history rows").locatedBy("//table/tbody/tr");

    private TransactionsPage() {
    }
}

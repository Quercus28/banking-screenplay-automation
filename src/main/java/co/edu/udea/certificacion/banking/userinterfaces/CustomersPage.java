package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

import static co.edu.udea.certificacion.banking.utils.XPath.literal;

/** Manager's Customers tab. */
public final class CustomersPage {

    public static final Target SEARCH = Target.the("Search Customer field")
            .locatedBy("//input[@ng-model='searchCustomer']");
    public static final Target ROWS = Target.the("customer list rows").locatedBy("//table/tbody/tr");
    public static final Target COLUMNS = Target.the("customer list columns").locatedBy("//table/thead//td");

    private CustomersPage() {
    }

    public static Target deleteButton(String firstName, String lastName) {
        return Target.the("Delete button of " + firstName + " " + lastName)
                .locatedBy("//tbody/tr[td[1][normalize-space()=" + literal(firstName) + "] and td[2][normalize-space()="
                        + literal(lastName) + "]]//button[normalize-space()='Delete']");
    }
}

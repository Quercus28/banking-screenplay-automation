package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Manager's Open Account tab. */
public final class OpenAccountForm {

    public static final String NO_CUSTOMER = "---Customer Name---";
    public static final String NO_CURRENCY = "---Currency---";

    public static final Target CUSTOMER = Target.the("Customer dropdown").locatedBy("#userSelect");
    public static final Target CURRENCY = Target.the("Currency dropdown").locatedBy("#currency");
    public static final Target PROCESS = Target.the("Process button")
            .locatedBy("//form[@name='myForm']//button[@type='submit']");

    private OpenAccountForm() {
    }

    public static Target field(String label) {
        switch (label) {
            case "Customer":
                return CUSTOMER;
            case "Currency":
                return CURRENCY;
            default:
                throw new IllegalArgumentException("Unknown field in Open Account: " + label);
        }
    }
}

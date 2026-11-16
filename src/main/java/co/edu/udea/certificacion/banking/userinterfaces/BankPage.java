package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

import static co.edu.udea.certificacion.banking.utils.XPath.literal;

/** Elements shared by every XYZ Bank view. */
public final class BankPage {

    public static final Target BODY = Target.the("page body").locatedBy("//body");
    public static final Target ERROR_MESSAGES = Target.the("error messages")
            .locatedBy("//*[contains(@class,'error') or contains(@class,'alert') or contains(@class,'help-block')]");

    private BankPage() {
    }

    public static Target button(String text) {
        return Target.the(text + " button").locatedBy("//button[normalize-space()=" + literal(text) + "]");
    }

    public static Target message(String text) {
        return Target.the("message " + text).locatedBy("//span[contains(normalize-space(.)," + literal(text) + ")]");
    }

    public static Target sortableHeader(String column) {
        return Target.the(column + " header").locatedBy("//thead//a[normalize-space()=" + literal(column) + "]");
    }
}

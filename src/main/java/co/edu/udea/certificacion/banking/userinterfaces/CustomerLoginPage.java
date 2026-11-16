package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Customer Login view. */
public final class CustomerLoginPage {

    public static final String NO_CUSTOMER = "---Your Name---";

    public static final Target CUSTOMER = Target.the("Your Name dropdown").locatedBy("#userSelect");
    public static final Target LOGIN = Target.the("Login button").locatedBy("//button[normalize-space()='Login']");

    private CustomerLoginPage() {
    }
}

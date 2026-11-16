package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Customer's account view. */
public final class AccountPage {

    private static final String INFORMATION_XPATH =
            "//div[contains(@class,'center') and contains(normalize-space(.),'Account Number')]";

    public static final Target WELCOME = Target.the("welcome message")
            .locatedBy("//strong[contains(normalize-space(.),'Welcome')]");
    public static final Target ACCOUNTS = Target.the("account dropdown").locatedBy("#accountSelect");
    public static final Target INFORMATION = Target.the("account information").locatedBy(INFORMATION_XPATH);
    public static final Target NUMBER = Target.the("account number").locatedBy(INFORMATION_XPATH + "/strong[1]");
    public static final Target BALANCE = Target.the("balance").locatedBy(INFORMATION_XPATH + "/strong[2]");
    public static final Target CURRENCY = Target.the("currency").locatedBy(INFORMATION_XPATH + "/strong[3]");

    private AccountPage() {
    }
}

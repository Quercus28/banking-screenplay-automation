package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Deposit and Withdrawl forms of the account view. */
public final class TransactionForm {

    public static final Target AMOUNT = Target.the("amount field").locatedBy("//input[@ng-model='amount']");
    public static final Target SUBMIT = Target.the("transaction submit button")
            .locatedBy("//form[@name='myForm']//button[@type='submit']");
    public static final Target MESSAGE = Target.the("transaction message").locatedBy("//span[contains(@class,'error')]");
    public static final Target DEPOSIT_LABEL = Target.the("Amount to be Deposited label")
            .locatedBy("//label[contains(normalize-space(.),'Amount to be Deposited')]");
    public static final Target WITHDRAWAL_LABEL = Target.the("Amount to be Withdrawn label")
            .locatedBy("//label[contains(normalize-space(.),'Amount to be Withdrawn')]");

    private TransactionForm() {
    }
}

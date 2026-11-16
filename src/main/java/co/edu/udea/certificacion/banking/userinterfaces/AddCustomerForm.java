package co.edu.udea.certificacion.banking.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

/** Manager's Add Customer tab. */
public final class AddCustomerForm {

    public static final Target FIRST_NAME = Target.the("First Name field").locatedBy("//input[@ng-model='fName']");
    public static final Target LAST_NAME = Target.the("Last Name field").locatedBy("//input[@ng-model='lName']");
    public static final Target POST_CODE = Target.the("Post Code field").locatedBy("//input[@ng-model='postCd']");
    public static final Target ADD_CUSTOMER = Target.the("Add Customer button of the form")
            .locatedBy("//form[@name='myForm']//button[@type='submit']");

    private AddCustomerForm() {
    }

    public static Target field(String label) {
        switch (label) {
            case "First Name":
                return FIRST_NAME;
            case "Last Name":
                return LAST_NAME;
            case "Post Code":
                return POST_CODE;
            default:
                throw new IllegalArgumentException("Unknown field in Add Customer: " + label);
        }
    }
}

package co.edu.udea.certificacion.banking.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Value and HTML5 validation state of a field. Native validation texts depend on the browser
 * language, so the validity property is checked instead of the message.
 */
public final class Field {

    private Field() {
    }

    public static Question<String> valueOf(Target field) {
        return Question.about("the value of " + field).answeredBy(actor -> {
            String value = field.resolveFor(actor).getWrappedElement().getAttribute("value");
            return value == null ? "" : value;
        });
    }

    public static Question<Boolean> isRequiredAndEmpty(Target field) {
        return validityState(field, "valueMissing");
    }

    /** A value that does not match the field's format or step, for example a decimal in an integer field. */
    public static Question<Boolean> hasAnInvalidValue(Target field) {
        return Question.about("whether " + field + " has an invalid value").answeredBy(actor ->
                validityState(field, "stepMismatch").answeredBy(actor)
                        || validityState(field, "badInput").answeredBy(actor)
                        || validityState(field, "typeMismatch").answeredBy(actor));
    }

    private static Question<Boolean> validityState(Target field, String state) {
        return Question.about("the " + state + " state of " + field).answeredBy(actor ->
                Boolean.TRUE.equals(BrowseTheWeb.as(actor).evaluateJavascript(
                        "return arguments[0].validity." + state + ";",
                        field.resolveFor(actor).getWrappedElement())));
    }
}

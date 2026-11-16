package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Sets the value of a datetime-local field (format yyyy-MM-ddTHH:mm[:ss]) and notifies AngularJS.
 * Typing it with the keyboard depends on the browser language, so it is set with JavaScript.
 */
public class EnterDateTime implements Interaction {

    private static final String SET_VALUE = "var field = arguments[0];"
            + "field.value = arguments[1];"
            + "field.dispatchEvent(new Event('input', {bubbles: true}));"
            + "field.dispatchEvent(new Event('change', {bubbles: true}));";

    private final String value;
    private final Target field;

    public EnterDateTime(String value, Target field) {
        this.value = value;
        this.field = field;
    }

    public static EnterDateTime with(String value, Target field) {
        return instrumented(EnterDateTime.class, value, field);
    }

    @Override
    @Step("{0} enters '#value' in #field")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).evaluateJavascript(SET_VALUE, field.resolveFor(actor).getWrappedElement(), value);
    }
}

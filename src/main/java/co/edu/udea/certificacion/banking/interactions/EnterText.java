package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Types a value into a field; if the value is empty, it only clears the field. */
public class EnterText implements Interaction {

    private final String value;
    private final Target field;

    public EnterText(String value, Target field) {
        this.value = value;
        this.field = field;
    }

    public static EnterText theValue(String value, Target field) {
        return instrumented(EnterText.class, value == null ? "" : value, field);
    }

    @Override
    @Step("{0} enters '#value' in #field")
    public <T extends Actor> void performAs(T actor) {
        if (value.isEmpty()) {
            actor.attemptsTo(Clear.field(field));
        } else {
            actor.attemptsTo(Enter.theValue(value).into(field));
        }
    }
}

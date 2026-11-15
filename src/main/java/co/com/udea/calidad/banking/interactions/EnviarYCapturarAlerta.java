package co.com.udea.calidad.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Pulsa un botón de envío con un clic directo de WebDriver y captura la alerta que pueda abrir.
 * El clic directo evita que una acción posterior al clic choque con la alerta abierta.
 */
public class EnviarYCapturarAlerta implements Interaction {

    private final Target boton;

    public EnviarYCapturarAlerta(Target boton) {
        this.boton = boton;
    }

    public static EnviarYCapturarAlerta con(Target boton) {
        return instrumented(EnviarYCapturarAlerta.class, boton);
    }

    @Override
    @Step("{0} pulsa #boton")
    public <T extends Actor> void performAs(T actor) {
        boton.resolveFor(actor).getWrappedElement().click();
        actor.attemptsTo(CapturarAlerta.siAparece());
    }
}

package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Asigna el valor de un campo datetime-local (formato yyyy-MM-ddTHH:mm[:ss]) y avisa a AngularJS.
 * Escribirlo con el teclado depende del idioma del navegador, por eso se asigna por JavaScript.
 */
public class EscribirFechaHora implements Interaction {

    private static final String ASIGNAR_VALOR = "var campo = arguments[0];"
            + "campo.value = arguments[1];"
            + "campo.dispatchEvent(new Event('input', {bubbles: true}));"
            + "campo.dispatchEvent(new Event('change', {bubbles: true}));";

    private final String valor;
    private final Target campo;

    public EscribirFechaHora(String valor, Target campo) {
        this.valor = valor;
        this.campo = campo;
    }

    public static EscribirFechaHora con(String valor, Target campo) {
        return instrumented(EscribirFechaHora.class, valor, campo);
    }

    @Override
    @Step("{0} escribe '#valor' en #campo")
    public <T extends Actor> void performAs(T actor) {
        BrowseTheWeb.as(actor).evaluateJavascript(ASIGNAR_VALOR, campo.resolveFor(actor).getWrappedElement(), valor);
    }
}

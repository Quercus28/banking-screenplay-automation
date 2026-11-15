package co.com.udea.calidad.banking.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Valor y estado de validación HTML5 de un campo. Los textos de validación nativos dependen del
 * idioma del navegador, por eso se consulta la propiedad validity en lugar del mensaje.
 */
public final class Campo {

    private Campo() {
    }

    public static Question<String> valorDe(Target campo) {
        return Question.about("el valor de " + campo).answeredBy(actor -> {
            String valor = campo.resolveFor(actor).getWrappedElement().getAttribute("value");
            return valor == null ? "" : valor;
        });
    }

    public static Question<Boolean> esObligatorioYEstaVacio(Target campo) {
        return estadoDeValidacion(campo, "valueMissing");
    }

    /** Valor que no respeta el formato o el paso del campo, por ejemplo un decimal en un campo de enteros. */
    public static Question<Boolean> tieneUnValorNoValido(Target campo) {
        return Question.about("si " + campo + " tiene un valor no válido").answeredBy(actor ->
                estadoDeValidacion(campo, "stepMismatch").answeredBy(actor)
                        || estadoDeValidacion(campo, "badInput").answeredBy(actor)
                        || estadoDeValidacion(campo, "typeMismatch").answeredBy(actor));
    }

    private static Question<Boolean> estadoDeValidacion(Target campo, String estado) {
        return Question.about("el estado " + estado + " de " + campo).answeredBy(actor ->
                Boolean.TRUE.equals(BrowseTheWeb.as(actor).evaluateJavascript(
                        "return arguments[0].validity." + estado + ";",
                        campo.resolveFor(actor).getWrappedElement())));
    }
}

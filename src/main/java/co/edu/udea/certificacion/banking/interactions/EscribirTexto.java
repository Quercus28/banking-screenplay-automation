package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Escribe un valor en un campo; si el valor está vacío, solo lo limpia. */
public class EscribirTexto implements Interaction {

    private final String valor;
    private final Target campo;

    public EscribirTexto(String valor, Target campo) {
        this.valor = valor;
        this.campo = campo;
    }

    public static EscribirTexto elValor(String valor, Target campo) {
        return instrumented(EscribirTexto.class, valor == null ? "" : valor, campo);
    }

    @Override
    @Step("{0} escribe '#valor' en #campo")
    public <T extends Actor> void performAs(T actor) {
        if (valor.isEmpty()) {
            actor.attemptsTo(Clear.field(campo));
        } else {
            actor.attemptsTo(Enter.theValue(valor).into(campo));
        }
    }
}

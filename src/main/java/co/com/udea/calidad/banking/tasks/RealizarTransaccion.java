package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.interactions.EscribirTexto;
import co.com.udea.calidad.banking.interactions.PulsarBoton;
import co.com.udea.calidad.banking.questions.ElementoVisible;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.calidad.banking.userinterfaces.FormularioTransaccion.ENVIAR;
import static co.com.udea.calidad.banking.userinterfaces.FormularioTransaccion.ETIQUETA_DEPOSITO;
import static co.com.udea.calidad.banking.userinterfaces.FormularioTransaccion.ETIQUETA_RETIRO;
import static co.com.udea.calidad.banking.userinterfaces.FormularioTransaccion.MONTO;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Deposita o retira un monto. Si el formulario ya está abierto lo reutiliza, igual que un usuario
 * que escribe un nuevo monto sin cambiar de pestaña. Espera la etiqueta del formulario antes de
 * escribir: si se escribe mientras la vista se reemplaza, el valor se pierde (HAL-17).
 */
public class RealizarTransaccion implements Task {

    private final String tipo;
    private final String monto;

    public RealizarTransaccion(String tipo, String monto) {
        this.tipo = tipo;
        this.monto = monto;
    }

    public static RealizarTransaccion deposito(String monto) {
        return instrumented(RealizarTransaccion.class, "deposito", monto);
    }

    public static RealizarTransaccion retiro(String monto) {
        return instrumented(RealizarTransaccion.class, "retiro", monto);
    }

    @Override
    @Step("{0} realiza un #tipo de '#monto'")
    public <T extends Actor> void performAs(T actor) {
        boolean esDeposito = "deposito".equals(tipo);
        Target etiqueta = esDeposito ? ETIQUETA_DEPOSITO : ETIQUETA_RETIRO;
        if (!actor.asksFor(ElementoVisible.de(etiqueta))) {
            actor.attemptsTo(
                    PulsarBoton.llamado(esDeposito ? "Deposit" : "Withdrawl"),
                    WaitUntil.the(etiqueta, isVisible()).forNoMoreThan(10).seconds()
            );
        }
        actor.attemptsTo(
                EscribirTexto.elValor(monto, MONTO),
                Click.on(ENVIAR)
        );
    }
}

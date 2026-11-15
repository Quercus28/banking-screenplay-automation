package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.interactions.PulsarBoton;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.calidad.banking.userinterfaces.FormularioTransaccion.ETIQUETA_RETIRO;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.SALDO;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Deja la cuenta recién abierta con el saldo indicado mediante un depósito. Al final abre la
 * pestaña Withdrawl, para que la siguiente operación empiece con un formulario limpio.
 */
public class PrepararSaldo implements Task {

    private final String saldo;

    public PrepararSaldo(String saldo) {
        this.saldo = saldo;
    }

    public static PrepararSaldo de(int saldo) {
        return instrumented(PrepararSaldo.class, String.valueOf(saldo));
    }

    @Override
    @Step("{0} deja la cuenta con saldo #saldo")
    public <T extends Actor> void performAs(T actor) {
        if ("0".equals(saldo)) {
            return;
        }
        actor.attemptsTo(
                RealizarTransaccion.deposito(saldo),
                WaitUntil.the(SALDO, containsText(saldo)).forNoMoreThan(5).seconds(),
                PulsarBoton.llamado("Withdrawl"),
                WaitUntil.the(ETIQUETA_RETIRO, isVisible()).forNoMoreThan(10).seconds()
        );
    }
}

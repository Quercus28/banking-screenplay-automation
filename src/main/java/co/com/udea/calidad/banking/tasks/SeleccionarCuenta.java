package co.com.udea.calidad.banking.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.CUENTAS;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.NUMERO;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;

public class SeleccionarCuenta implements Task {

    private final String numero;

    public SeleccionarCuenta(String numero) {
        this.numero = numero;
    }

    public static SeleccionarCuenta numero(String numero) {
        return instrumented(SeleccionarCuenta.class, numero);
    }

    @Override
    @Step("{0} selecciona la cuenta '#numero'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(numero).from(CUENTAS),
                WaitUntil.the(NUMERO, containsText(numero)).forNoMoreThan(5).seconds()
        );
    }
}

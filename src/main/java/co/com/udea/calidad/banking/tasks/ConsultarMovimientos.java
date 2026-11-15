package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.interactions.PulsarBoton;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.boton;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ConsultarMovimientos implements Task {

    public static ConsultarMovimientos deLaCuenta() {
        return instrumented(ConsultarMovimientos.class);
    }

    @Override
    @Step("{0} consulta los movimientos de la cuenta")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                PulsarBoton.llamado("Transactions"),
                WaitUntil.the(boton("Back"), isVisible()).forNoMoreThan(10).seconds()
        );
    }
}

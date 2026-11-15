package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.interactions.PulsarBoton;
import co.com.udea.calidad.banking.questions.ElementoVisible;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.boton;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IngresarComoAdministrador implements Task {

    public static IngresarComoAdministrador alBanco() {
        return instrumented(IngresarComoAdministrador.class);
    }

    @Override
    @Step("{0} ingresa como administrador (Bank Manager Login)")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(ElementoVisible.de(boton("Bank Manager Login")))) {
            actor.attemptsTo(PulsarBoton.llamado("Home"));
        }
        actor.attemptsTo(
                PulsarBoton.llamado("Bank Manager Login"),
                WaitUntil.the(boton("Customers"), isVisible()).forNoMoreThan(10).seconds()
        );
    }
}

package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.ReiniciarDatosDelBanco;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.edu.udea.certificacion.banking.userinterfaces.PaginaBanco.boton;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AbrirBanco implements Task {

    public static AbrirBanco conDatosIniciales() {
        return instrumented(AbrirBanco.class);
    }

    @Override
    @Step("{0} abre XYZ Bank con los datos de demostración")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ReiniciarDatosDelBanco.yAbrir("#/login"),
                WaitUntil.the(boton("Bank Manager Login"), isVisible()).forNoMoreThan(15).seconds()
        );
    }
}

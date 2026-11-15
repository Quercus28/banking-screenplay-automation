package co.edu.udea.certificacion.banking.tasks;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.edu.udea.certificacion.banking.userinterfaces.PaginaBanco.encabezadoOrdenable;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Pulsa el encabezado de una columna ordenable (listado de clientes o de movimientos). */
public class OrdenarListado implements Task {

    private final String columna;

    public OrdenarListado(String columna) {
        this.columna = columna;
    }

    public static OrdenarListado porLaColumna(String columna) {
        return instrumented(OrdenarListado.class, columna);
    }

    @Override
    @Step("{0} ordena el listado por la columna '#columna'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(encabezadoOrdenable(columna)));
    }
}

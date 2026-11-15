package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.models.Cliente;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

import static co.edu.udea.certificacion.banking.userinterfaces.ListadoClientes.botonEliminar;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class EliminarCliente implements Task {

    private final String nombreCompleto;

    public EliminarCliente(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public static EliminarCliente llamado(String nombreCompleto) {
        return instrumented(EliminarCliente.class, nombreCompleto);
    }

    @Override
    @Step("{0} elimina al cliente '#nombreCompleto'")
    public <T extends Actor> void performAs(T actor) {
        Cliente cliente = Cliente.llamado(nombreCompleto, "");
        actor.attemptsTo(
                BuscarCliente.sinFiltro(),
                Click.on(botonEliminar(cliente.getNombre(), cliente.getApellido()))
        );
    }
}

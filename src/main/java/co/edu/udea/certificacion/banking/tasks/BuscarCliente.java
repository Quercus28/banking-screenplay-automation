package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.EscribirTexto;
import co.edu.udea.certificacion.banking.questions.ElementoVisible;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.edu.udea.certificacion.banking.userinterfaces.ListadoClientes.BUSCADOR;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class BuscarCliente implements Task {

    private final String texto;

    public BuscarCliente(String texto) {
        this.texto = texto;
    }

    public static BuscarCliente con(String texto) {
        return instrumented(BuscarCliente.class, texto);
    }

    public static BuscarCliente sinFiltro() {
        return con("");
    }

    @Override
    @Step("{0} busca '#texto' en el listado de clientes")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(ElementoVisible.de(BUSCADOR))) {
            actor.attemptsTo(IrALaPestana.delAdministrador("Customers"));
        }
        actor.attemptsTo(EscribirTexto.elValor(texto, BUSCADOR));
    }
}

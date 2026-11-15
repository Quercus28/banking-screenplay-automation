package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.interactions.EscribirTexto;
import co.com.udea.calidad.banking.questions.ElementoVisible;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.com.udea.calidad.banking.userinterfaces.ListadoClientes.BUSCADOR;
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

package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.models.Cliente;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Crea un cliente con una cuenta como administrador e inicia sesión con ese cliente. */
public class PrepararClienteConCuenta implements Task {

    private final String cliente;
    private final String moneda;

    public PrepararClienteConCuenta(String cliente, String moneda) {
        this.cliente = cliente;
        this.moneda = moneda;
    }

    public static PrepararClienteConCuenta en(String cliente, String moneda) {
        return instrumented(PrepararClienteConCuenta.class, cliente, moneda);
    }

    @Override
    @Step("{0} prepara al cliente '#cliente' con una cuenta en '#moneda'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                AbrirBanco.conDatosIniciales(),
                RegistrarCliente.con(Cliente.llamado(cliente, "QA0001")),
                AbrirCuenta.en(moneda, cliente),
                IniciarSesionCliente.como(cliente)
        );
    }
}

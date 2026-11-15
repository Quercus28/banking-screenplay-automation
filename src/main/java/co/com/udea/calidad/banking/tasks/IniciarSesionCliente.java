package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.interactions.PulsarBoton;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static co.com.udea.calidad.banking.userinterfaces.InicioSesionCliente.CLIENTE;
import static co.com.udea.calidad.banking.userinterfaces.InicioSesionCliente.LOGIN;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.BIENVENIDA;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesionCliente implements Task {

    private final String cliente;

    public IniciarSesionCliente(String cliente) {
        this.cliente = cliente;
    }

    public static IniciarSesionCliente como(String cliente) {
        return instrumented(IniciarSesionCliente.class, cliente);
    }

    @Override
    @Step("{0} inicia sesión como el cliente '#cliente'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                AbrirInicioSesionCliente.sinSeleccionarNombre(),
                SelectFromOptions.byVisibleText(cliente).from(CLIENTE),
                Click.on(LOGIN),
                WaitUntil.the(BIENVENIDA, isVisible()).forNoMoreThan(10).seconds()
        );
    }

    /** Lleva al actor a la vista Customer Login desde cualquier vista. */
    public static class AbrirInicioSesionCliente implements Task {

        public static AbrirInicioSesionCliente sinSeleccionarNombre() {
            return instrumented(AbrirInicioSesionCliente.class);
        }

        @Override
        @Step("{0} abre el inicio de sesión de cliente")
        public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    PulsarBoton.llamado("Home"),
                    PulsarBoton.llamado("Customer Login"),
                    WaitUntil.the(CLIENTE, isVisible()).forNoMoreThan(10).seconds()
            );
        }
    }
}

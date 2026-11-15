package co.com.udea.calidad.banking.tasks;

import co.com.udea.calidad.banking.interactions.EnviarYCapturarAlerta;
import co.com.udea.calidad.banking.questions.ElementoVisible;
import co.com.udea.calidad.banking.utils.Memoria;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import java.util.ArrayList;

import static co.com.udea.calidad.banking.userinterfaces.FormularioCuenta.CLIENTE;
import static co.com.udea.calidad.banking.userinterfaces.FormularioCuenta.MONEDA;
import static co.com.udea.calidad.banking.userinterfaces.FormularioCuenta.PROCESAR;
import static co.com.udea.calidad.banking.userinterfaces.FormularioCuenta.SIN_CLIENTE;
import static co.com.udea.calidad.banking.userinterfaces.FormularioCuenta.SIN_MONEDA;
import static co.com.udea.calidad.banking.utils.Constantes.NUMEROS_CUENTA;
import static co.com.udea.calidad.banking.utils.Constantes.PREFIJO_CUENTA_CREADA;
import static co.com.udea.calidad.banking.utils.Constantes.ULTIMA_ALERTA;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Procesa Open Account. Un cliente o una moneda vacíos seleccionan la opción en blanco de forma
 * explícita, porque el formulario conserva la selección anterior al volver a la pestaña.
 */
public class AbrirCuenta implements Task {

    private final String moneda;
    private final String cliente;

    public AbrirCuenta(String moneda, String cliente) {
        this.moneda = moneda;
        this.cliente = cliente;
    }

    public static AbrirCuenta en(String moneda, String cliente) {
        return instrumented(AbrirCuenta.class, moneda, cliente);
    }

    @Override
    @Step("{0} abre una cuenta en '#moneda' para el cliente '#cliente'")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(ElementoVisible.de(MONEDA))) {
            actor.attemptsTo(IrALaPestana.delAdministrador("Open Account"));
        }
        actor.attemptsTo(
                SelectFromOptions.byVisibleText(cliente.isEmpty() ? SIN_CLIENTE : cliente).from(CLIENTE),
                SelectFromOptions.byVisibleText(moneda.isEmpty() ? SIN_MONEDA : moneda).from(MONEDA),
                EnviarYCapturarAlerta.con(PROCESAR)
        );
        String alerta = actor.recall(ULTIMA_ALERTA);
        if (alerta.startsWith(PREFIJO_CUENTA_CREADA)) {
            String numero = alerta.substring(PREFIJO_CUENTA_CREADA.length()).trim();
            Memoria.<String>lista(actor, NUMEROS_CUENTA).add(numero);
            Memoria.cuentasPorCliente(actor).computeIfAbsent(cliente, clave -> new ArrayList<>()).add(numero);
        }
    }
}

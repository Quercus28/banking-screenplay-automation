package co.edu.udea.certificacion.banking.tasks;

import co.edu.udea.certificacion.banking.interactions.EnviarYCapturarAlerta;
import co.edu.udea.certificacion.banking.interactions.EscribirTexto;
import co.edu.udea.certificacion.banking.models.Cliente;
import co.edu.udea.certificacion.banking.questions.ElementoVisible;
import co.edu.udea.certificacion.banking.utils.Memoria;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.edu.udea.certificacion.banking.userinterfaces.FormularioCliente.AGREGAR;
import static co.edu.udea.certificacion.banking.userinterfaces.FormularioCliente.APELLIDO;
import static co.edu.udea.certificacion.banking.userinterfaces.FormularioCliente.CODIGO_POSTAL;
import static co.edu.udea.certificacion.banking.userinterfaces.FormularioCliente.NOMBRE;
import static co.edu.udea.certificacion.banking.utils.Constantes.IDENTIFICADORES_CLIENTES;
import static co.edu.udea.certificacion.banking.utils.Constantes.PREFIJO_CLIENTE_CREADO;
import static co.edu.udea.certificacion.banking.utils.Constantes.ULTIMA_ALERTA;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Diligencia Add Customer, lo envía y recuerda la alerta y el identificador asignado. */
public class RegistrarCliente implements Task {

    private final String nombre;
    private final String apellido;
    private final String codigoPostal;

    public RegistrarCliente(String nombre, String apellido, String codigoPostal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.codigoPostal = codigoPostal;
    }

    public static RegistrarCliente con(String nombre, String apellido, String codigoPostal) {
        return instrumented(RegistrarCliente.class, nulo(nombre), nulo(apellido), nulo(codigoPostal));
    }

    public static RegistrarCliente con(Cliente cliente) {
        return con(cliente.getNombre(), cliente.getApellido(), cliente.getCodigoPostal());
    }

    private static String nulo(String valor) {
        return valor == null ? "" : valor;
    }

    @Override
    @Step("{0} registra al cliente '#nombre' '#apellido' con código postal '#codigoPostal'")
    public <T extends Actor> void performAs(T actor) {
        if (!actor.asksFor(ElementoVisible.de(NOMBRE))) {
            actor.attemptsTo(IrALaPestana.delAdministrador("Add Customer"));
        }
        actor.attemptsTo(
                EscribirTexto.elValor(nombre, NOMBRE),
                EscribirTexto.elValor(apellido, APELLIDO),
                EscribirTexto.elValor(codigoPostal, CODIGO_POSTAL),
                EnviarYCapturarAlerta.con(AGREGAR)
        );
        String alerta = actor.recall(ULTIMA_ALERTA);
        if (alerta.startsWith(PREFIJO_CLIENTE_CREADO)) {
            Memoria.<Integer>lista(actor, IDENTIFICADORES_CLIENTES)
                    .add(Integer.valueOf(alerta.substring(PREFIJO_CLIENTE_CREADO.length()).trim()));
        }
    }
}

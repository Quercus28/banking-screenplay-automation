package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.interactions.ReiniciarDatosDelBanco;
import co.edu.udea.certificacion.banking.models.Cliente;
import co.edu.udea.certificacion.banking.models.FilaCliente;
import co.edu.udea.certificacion.banking.questions.Campo;
import co.edu.udea.certificacion.banking.questions.ListadoDeClientes;
import co.edu.udea.certificacion.banking.questions.Selector;
import co.edu.udea.certificacion.banking.questions.UltimaAlerta;
import co.edu.udea.certificacion.banking.tasks.AbrirBanco;
import co.edu.udea.certificacion.banking.tasks.AbrirCuenta;
import co.edu.udea.certificacion.banking.tasks.BuscarCliente;
import co.edu.udea.certificacion.banking.tasks.EliminarCliente;
import co.edu.udea.certificacion.banking.tasks.IniciarSesionCliente;
import co.edu.udea.certificacion.banking.tasks.IrALaPestana;
import co.edu.udea.certificacion.banking.tasks.OrdenarListado;
import co.edu.udea.certificacion.banking.tasks.RegistrarCliente;
import co.edu.udea.certificacion.banking.userinterfaces.FormularioCliente;
import co.edu.udea.certificacion.banking.userinterfaces.FormularioCuenta;
import co.edu.udea.certificacion.banking.userinterfaces.InicioSesionCliente;
import co.edu.udea.certificacion.banking.userinterfaces.ListadoClientes;
import co.edu.udea.certificacion.banking.utils.Memoria;
import co.edu.udea.certificacion.banking.utils.Orden;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static co.edu.udea.certificacion.banking.stepdefinitions.Verificacion.actor;
import static co.edu.udea.certificacion.banking.stepdefinitions.Verificacion.verificarQue;
import static co.edu.udea.certificacion.banking.utils.Constantes.CLIENTES_PRECARGADOS;
import static co.edu.udea.certificacion.banking.utils.Constantes.IDENTIFICADORES_CLIENTES;
import static co.edu.udea.certificacion.banking.utils.Constantes.NUMEROS_CUENTA;
import static co.edu.udea.certificacion.banking.utils.Constantes.PREFIJO_CLIENTE_CREADO;
import static co.edu.udea.certificacion.banking.utils.Constantes.PREFIJO_CUENTA_CREADA;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;

/** Pasos del administrador: registro de clientes, apertura de cuentas y gestión del listado. */
public class AdministradorStepDefinitions {

    @Dado("que el administrador está en la pestaña {string}")
    public void queElAdministradorEstaEnLaPestana(String pestana) {
        actor().wasAbleTo(AbrirBanco.conDatosIniciales(), IrALaPestana.delAdministrador(pestana));
    }

    @Dado("está en la pestaña {string}")
    public void estaEnLaPestana(String pestana) {
        actor().wasAbleTo(IrALaPestana.delAdministrador(pestana));
    }

    @Dado("que el administrador registró los clientes")
    public void queElAdministradorRegistroLosClientes(List<Map<String, String>> clientes) {
        actor().wasAbleTo(AbrirBanco.conDatosIniciales());
        clientes.forEach(fila -> actor().wasAbleTo(RegistrarCliente.con(Cliente.desde(fila))));
    }

    @Dado("que registró un cliente con nombre {string}, apellido {string} y código postal {string}")
    @Cuando("registra un cliente con nombre {string}, apellido {string} y código postal {string}")
    public void registrarUnCliente(String nombre, String apellido, String codigoPostal) {
        actor().attemptsTo(RegistrarCliente.con(nombre, apellido, codigoPostal));
    }

    @Cuando("envía el formulario de registro sin diligenciar ningún campo")
    public void enviarElFormularioVacio() {
        actor().attemptsTo(RegistrarCliente.con("", "", ""));
    }

    @Dado("(que )abrió una cuenta en {string} para el cliente {string}")
    @Cuando("abre una cuenta en {string} para el cliente {string}")
    public void abrirUnaCuenta(String moneda, String cliente) {
        actor().attemptsTo(AbrirCuenta.en(moneda, cliente));
    }

    @Cuando("procesa la apertura de una cuenta en {string} sin seleccionar cliente")
    public void abrirCuentaSinCliente(String moneda) {
        actor().attemptsTo(AbrirCuenta.en(moneda, ""));
    }

    @Cuando("procesa la apertura de una cuenta para el cliente {string} sin seleccionar moneda")
    public void abrirCuentaSinMoneda(String cliente) {
        actor().attemptsTo(AbrirCuenta.en("", cliente));
    }

    @Cuando("abre la aplicación en una sesión nueva del navegador como administrador")
    public void abrirEnUnaSesionNuevaComoAdministrador() {
        actor().attemptsTo(
                ReiniciarDatosDelBanco.yAbrir("#/manager/list"),
                WaitUntil.the(ListadoClientes.BUSCADOR, isVisible()).forNoMoreThan(15).seconds()
        );
    }

    @Cuando("busca {string} en el listado de clientes")
    public void buscarEnElListado(String texto) {
        actor().attemptsTo(BuscarCliente.con(texto));
    }

    @Cuando("ordena el listado por la columna {string}")
    public void ordenarElListado(String columna) {
        actor().attemptsTo(OrdenarListado.porLaColumna(columna));
    }

    @Cuando("elimina al cliente {string}")
    public void eliminarAlCliente(String cliente) {
        actor().attemptsTo(EliminarCliente.llamado(cliente));
    }

    @Entonces("se muestra la alerta {string}")
    public void seMuestraLaAlerta(String texto) {
        verificarQue(UltimaAlerta.texto(), equalTo(texto));
    }

    @Entonces("no se muestra la alerta {string}")
    public void noSeMuestraLaAlerta(String texto) {
        verificarQue(UltimaAlerta.texto(), not(equalTo(texto)));
    }

    @Entonces("no se muestra ninguna alerta")
    public void noSeMuestraNingunaAlerta() {
        verificarQue(UltimaAlerta.texto(), equalTo(""));
    }

    @Entonces("se muestra la alerta {string} seguida del identificador asignado")
    public void seMuestraLaAlertaConElIdentificador(String prefijo) {
        List<Integer> identificadores = Memoria.lista(actor(), IDENTIFICADORES_CLIENTES);
        verificarQue(UltimaAlerta.texto(), startsWith(prefijo));
        verificarQue(UltimaAlerta.texto(), equalTo(PREFIJO_CLIENTE_CREADO + ultimo(identificadores)));
    }

    @Entonces("se muestra la alerta {string} seguida del número de cuenta asignado")
    public void seMuestraLaAlertaConElNumeroDeCuenta(String prefijo) {
        List<String> cuentas = Memoria.lista(actor(), NUMEROS_CUENTA);
        verificarQue(UltimaAlerta.texto(), startsWith(prefijo));
        verificarQue(UltimaAlerta.texto(), equalTo(PREFIJO_CUENTA_CREADA + ultimo(cuentas)));
    }

    @Entonces("el identificador asignado es el siguiente al del cliente anterior")
    public void elIdentificadorEsConsecutivo() {
        verificarQue(consecutivos(IDENTIFICADORES_CLIENTES), is(true));
    }

    @Entonces("el número de cuenta asignado es el siguiente al de la cuenta anterior")
    public void elNumeroDeCuentaEsConsecutivo() {
        verificarQue(consecutivos(NUMEROS_CUENTA), is(true));
    }

    @Entonces("el formulario de registro queda vacío")
    public void elFormularioQuedaVacio() {
        verificarQue(Campo.valorDe(FormularioCliente.NOMBRE), equalTo(""));
        verificarQue(Campo.valorDe(FormularioCliente.APELLIDO), equalTo(""));
        verificarQue(Campo.valorDe(FormularioCliente.CODIGO_POSTAL), equalTo(""));
    }

    @Entonces("el campo {string} muestra la validación de campo obligatorio")
    public void elCampoEsObligatorio(String campo) {
        verificarQue(Campo.esObligatorioYEstaVacio(FormularioCliente.campo(campo)), is(true));
    }

    @Entonces("el campo {string} muestra la validación de selección obligatoria")
    public void elSelectorEsObligatorio(String campo) {
        verificarQue(Campo.esObligatorioYEstaVacio(FormularioCuenta.campo(campo)), is(true));
    }

    @Entonces("no se registra ningún cliente nuevo")
    public void noSeRegistraNingunClienteNuevo() {
        int registrados = Memoria.lista(actor(), IDENTIFICADORES_CLIENTES).size();
        actor().attemptsTo(BuscarCliente.sinFiltro());
        verificarQue(ListadoDeClientes.cantidad(), equalTo(CLIENTES_PRECARGADOS + registrados));
    }

    @Entonces("no se crea ninguna cuenta nueva")
    public void noSeCreaNingunaCuentaNueva() {
        verificarQue(UltimaAlerta.texto(), not(startsWith(PREFIJO_CUENTA_CREADA)));
    }

    @Entonces("el sistema rechaza el registro por formato de nombre inválido")
    public void elSistemaRechazaElFormatoDeNombre() {
        verificarQue(UltimaAlerta.texto(), not(startsWith(PREFIJO_CLIENTE_CREADO)));
    }

    @Entonces("el sistema informa que los campos son obligatorios o inválidos")
    public void elSistemaInformaCamposInvalidos() {
        verificarQue(Question.about("si se informa que los campos son obligatorios o inválidos").answeredBy(actor -> {
            String alerta = UltimaAlerta.texto().answeredBy(actor).toLowerCase();
            boolean alertaDeCampos = alerta.contains("required") || alerta.contains("invalid")
                    || alerta.contains("obligatori") || alerta.contains("inválid");
            boolean validacionNativa = Campo.esObligatorioYEstaVacio(FormularioCliente.NOMBRE).answeredBy(actor)
                    || Campo.esObligatorioYEstaVacio(FormularioCliente.APELLIDO).answeredBy(actor)
                    || Campo.esObligatorioYEstaVacio(FormularioCliente.CODIGO_POSTAL).answeredBy(actor);
            return alertaDeCampos || validacionNativa;
        }), is(true));
    }

    @Entonces("el cliente {string} {string} con código postal {string} aparece en el listado sin número de cuenta")
    public void elClienteApareceSinCuenta(String nombre, String apellido, String codigoPostal) {
        actor().attemptsTo(BuscarCliente.con(apellido));
        verificarQue(filaDe(nombre, apellido), equalTo(Optional.of(nombre + " | " + apellido + " | " + codigoPostal + " | ")));
    }

    @Entonces("la búsqueda {string} en el listado de clientes devuelve {int} cliente(s)")
    public void laBusquedaDevuelve(String texto, int cantidad) {
        actor().attemptsTo(BuscarCliente.con(texto));
        verificarQue(ListadoDeClientes.cantidad(), equalTo(cantidad));
    }

    @Entonces("el listado muestra {int} cliente(s)")
    public void elListadoMuestraLaCantidad(int cantidad) {
        verificarQue(ListadoDeClientes.cantidad(), equalTo(cantidad));
    }

    @Entonces("el listado de clientes tiene las columnas {string}, {string}, {string}, {string} y {string}")
    public void elListadoTieneLasColumnas(String c1, String c2, String c3, String c4, String c5) {
        verificarQue(ListadoDeClientes.columnas(), contains(c1, c2, c3, c4, c5));
    }

    @Entonces("cada cliente tiene un botón {string}")
    public void cadaClienteTieneUnBoton(String texto) {
        verificarQue(ListadoDeClientes.todasLasFilasTienenElBoton(texto), is(true));
    }

    @Entonces("el listado muestra únicamente al cliente {string} {string} con código postal {string} y cuentas {string}")
    public void elListadoMuestraUnicamenteLaFila(String nombre, String apellido, String codigoPostal, String cuentas) {
        verificarQue(Question.about("las filas del listado").answeredBy(
                        actor -> ListadoDeClientes.filas().answeredBy(actor).toString()),
                equalTo("[" + nombre + " | " + apellido + " | " + codigoPostal + " | " + cuentas + "]"));
    }

    @Entonces("el listado muestra al cliente {string} {string} con código postal {string}")
    public void elListadoMuestraAlCliente(String nombre, String apellido, String codigoPostal) {
        verificarQue(Question.about("el código postal del cliente").answeredBy(actor ->
                        ListadoDeClientes.filas().answeredBy(actor).stream()
                                .filter(fila -> fila.es(nombre, apellido))
                                .map(FilaCliente::getCodigoPostal)
                                .findFirst()),
                equalTo(Optional.of(codigoPostal)));
    }

    @Entonces("el listado muestra los clientes")
    public void elListadoMuestraLosClientes(List<String> clientes) {
        verificarQue(ListadoDeClientes.nombresCompletos(), containsInAnyOrder(clientes.toArray(new String[0])));
    }

    @Entonces("el listado muestra únicamente al cliente {string}")
    public void elListadoMuestraUnicamenteAlCliente(String cliente) {
        verificarQue(ListadoDeClientes.nombresCompletos(), contains(cliente));
    }

    @Entonces("el listado queda ordenado por {string} de forma {word}")
    public void elListadoQuedaOrdenado(String columna, String sentido) {
        verificarQue(ListadoDeClientes.valoresDeLaColumna(columna),
                "descendente".equals(sentido) ? Orden.textosDescendentes() : Orden.textosAscendentes());
    }

    @Entonces("el cliente {string} tiene {int} cuentas en el listado de clientes")
    public void elClienteTieneCuentas(String cliente, int cantidad) {
        actor().attemptsTo(BuscarCliente.sinFiltro());
        verificarQue(cuentasDe(cliente), equalTo(cantidad));
    }

    @Entonces("en el listado de clientes cada cuenta aparece junto a su cliente")
    public void cadaCuentaApareceJuntoASuCliente(List<Map<String, String>> esperado) {
        actor().attemptsTo(BuscarCliente.sinFiltro());
        Map<String, List<String>> abiertas = Memoria.cuentasPorCliente(actor());
        for (Map<String, String> fila : esperado) {
            String cliente = fila.get("cliente");
            verificarQue(cuentasDe(cliente), equalTo(Integer.valueOf(fila.get("cuentas"))));
            verificarQue(numerosDeCuentaDe(cliente), hasItems(abiertas.get(cliente).toArray(new String[0])));
        }
    }

    @Entonces("el selector de moneda ofrece las opciones {string}, {string} y {string}")
    public void elSelectorDeMonedaOfrece(String primera, String segunda, String tercera) {
        verificarQue(Selector.opcionesDe(FormularioCuenta.MONEDA), hasItems(primera, segunda, tercera));
    }

    @Entonces("los selectores de cliente y moneda vuelven a su opción vacía")
    public void losSelectoresVuelvenASuOpcionVacia() {
        verificarQue(Selector.opcionSeleccionadaEn(FormularioCuenta.CLIENTE), equalTo(FormularioCuenta.SIN_CLIENTE));
        verificarQue(Selector.opcionSeleccionadaEn(FormularioCuenta.MONEDA), equalTo(FormularioCuenta.SIN_MONEDA));
    }

    @Entonces("el cliente {string} no aparece en el selector de clientes de {string}")
    public void elClienteNoApareceEnElSelector(String cliente, String vista) {
        if ("Open Account".equals(vista)) {
            actor().attemptsTo(IrALaPestana.delAdministrador("Open Account"));
            verificarQue(Selector.opcionesDe(FormularioCuenta.CLIENTE), not(hasItems(cliente)));
        } else {
            actor().attemptsTo(IniciarSesionCliente.AbrirInicioSesionCliente.sinSeleccionarNombre());
            verificarQue(Selector.opcionesDe(InicioSesionCliente.CLIENTE), not(hasItems(cliente)));
        }
    }

    private static Question<Optional<String>> filaDe(String nombre, String apellido) {
        return Question.about("la fila de " + nombre + " " + apellido).answeredBy(actor ->
                ListadoDeClientes.filas().answeredBy(actor).stream()
                        .filter(fila -> fila.es(nombre, apellido))
                        .map(FilaCliente::toString)
                        .findFirst());
    }

    private static Question<List<String>> numerosDeCuentaDe(String cliente) {
        return Question.about("las cuentas de " + cliente).answeredBy(actor ->
                ListadoDeClientes.filas().answeredBy(actor).stream()
                        .filter(fila -> fila.getNombreCompleto().equals(cliente))
                        .map(FilaCliente::getListaCuentas)
                        .findFirst()
                        .orElse(List.of()));
    }

    private static Question<Integer> cuentasDe(String cliente) {
        return Question.about("la cantidad de cuentas de " + cliente)
                .answeredBy(actor -> numerosDeCuentaDe(cliente).answeredBy(actor).size());
    }

    private static Question<Boolean> consecutivos(String clave) {
        return Question.about("si los dos últimos valores son consecutivos").answeredBy(actor -> {
            List<Object> valores = Memoria.lista(actor, clave);
            if (valores.size() < 2) {
                return false;
            }
            long anterior = Long.parseLong(valores.get(valores.size() - 2).toString());
            long actual = Long.parseLong(valores.get(valores.size() - 1).toString());
            return actual == anterior + 1;
        });
    }

    private static <T> String ultimo(List<T> valores) {
        return valores.isEmpty() ? "(ninguno)" : valores.get(valores.size() - 1).toString();
    }
}

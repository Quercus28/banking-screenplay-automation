package co.com.udea.calidad.banking.stepdefinitions;

import co.com.udea.calidad.banking.interactions.PulsarBoton;
import co.com.udea.calidad.banking.questions.Campo;
import co.com.udea.calidad.banking.questions.ElementoVisible;
import co.com.udea.calidad.banking.questions.Selector;
import co.com.udea.calidad.banking.questions.TextoDe;
import co.com.udea.calidad.banking.tasks.AbrirBanco;
import co.com.udea.calidad.banking.tasks.IniciarSesionCliente;
import co.com.udea.calidad.banking.tasks.PrepararClienteConCuenta;
import co.com.udea.calidad.banking.tasks.PrepararSaldo;
import co.com.udea.calidad.banking.tasks.RealizarTransaccion;
import co.com.udea.calidad.banking.tasks.SeleccionarCuenta;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actions.SelectFromOptions;

import java.util.List;

import static co.com.udea.calidad.banking.stepdefinitions.Verificacion.actor;
import static co.com.udea.calidad.banking.stepdefinitions.Verificacion.verificarQue;
import static co.com.udea.calidad.banking.userinterfaces.FormularioTransaccion.MENSAJE;
import static co.com.udea.calidad.banking.userinterfaces.FormularioTransaccion.MONTO;
import static co.com.udea.calidad.banking.userinterfaces.InicioSesionCliente.CLIENTE;
import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.boton;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.BIENVENIDA;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.CUENTAS;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.INFORMACION;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.MONEDA;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.NUMERO;
import static co.com.udea.calidad.banking.userinterfaces.PaginaCuenta.SALDO;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

/** Pasos del cliente: sesión, cuentas, depósitos y retiros. */
public class ClienteStepDefinitions {

    @Dado("que el cliente {string} inició sesión")
    public void queElClienteInicioSesion(String cliente) {
        actor().wasAbleTo(AbrirBanco.conDatosIniciales(), IniciarSesionCliente.como(cliente));
    }

    @Cuando("inicia sesión como el cliente {string}")
    public void iniciaSesionComoElCliente(String cliente) {
        actor().attemptsTo(IniciarSesionCliente.como(cliente));
    }

    @Cuando("abre el inicio de sesión de cliente sin seleccionar un nombre")
    public void abreElInicioDeSesionDeCliente() {
        actor().attemptsTo(IniciarSesionCliente.AbrirInicioSesionCliente.sinSeleccionarNombre());
    }

    @Cuando("selecciona el cliente {string}")
    @Cuando("vuelve a la opción {string}")
    public void seleccionaElCliente(String opcion) {
        actor().attemptsTo(SelectFromOptions.byVisibleText(opcion).from(CLIENTE));
    }

    @Cuando("selecciona la cuenta {string}")
    public void seleccionaLaCuenta(String numero) {
        actor().attemptsTo(SeleccionarCuenta.numero(numero));
    }

    @Cuando("cierra sesión")
    @Dado("cerró sesión")
    public void cierraSesion() {
        actor().attemptsTo(PulsarBoton.llamado("Logout"));
    }

    @Dado("que el cliente {string} tiene una cuenta en {string}")
    public void queElClienteTieneUnaCuenta(String cliente, String moneda) {
        actor().wasAbleTo(PrepararClienteConCuenta.en(cliente, moneda));
    }

    @Dado("que la cuenta tiene saldo {int}")
    public void queLaCuentaTieneSaldo(int saldo) {
        actor().wasAbleTo(PrepararSaldo.de(saldo));
    }

    @Dado("acaba de depositar {int} con éxito")
    public void acabaDeDepositarConExito(int monto) {
        actor().wasAbleTo(RealizarTransaccion.deposito(String.valueOf(monto)));
        verificarQue(TextoDe.el(MENSAJE), equalTo("Deposit Successful"));
    }

    @Cuando("deposita {word}")
    @Cuando("intenta escribir {string} como monto del depósito y lo envía")
    public void deposita(String monto) {
        actor().attemptsTo(RealizarTransaccion.deposito(monto));
    }

    @Cuando("retira {word}")
    @Cuando("intenta escribir {string} como monto del retiro y lo envía")
    public void retira(String monto) {
        actor().attemptsTo(RealizarTransaccion.retiro(monto));
    }

    @Cuando("envía el depósito sin escribir un monto")
    public void enviaElDepositoSinMonto() {
        actor().attemptsTo(RealizarTransaccion.deposito(""));
    }

    @Cuando("envía el retiro sin escribir un monto")
    public void enviaElRetiroSinMonto() {
        actor().attemptsTo(RealizarTransaccion.retiro(""));
    }

    @Entonces("se muestra el mensaje de bienvenida {string}")
    public void seMuestraElMensajeDeBienvenida(String mensaje) {
        verificarQue(TextoDe.el(BIENVENIDA), equalTo(mensaje));
    }

    @Entonces("el selector de clientes lista los clientes precargados")
    public void elSelectorListaLosClientesPrecargados(List<String> clientes) {
        verificarQue(Selector.opcionesDe(CLIENTE), hasItems(clientes.toArray(new String[0])));
    }

    @Entonces("el selector de clientes muestra la opción {string}")
    public void elSelectorDeClientesMuestraLaOpcion(String opcion) {
        verificarQue(Selector.opcionSeleccionadaEn(CLIENTE), equalTo(opcion));
    }

    @Entonces("el selector de cuentas ofrece las cuentas {string}, {string} y {string}")
    public void elSelectorDeCuentasOfrece(String primera, String segunda, String tercera) {
        verificarQue(Selector.opcionesDe(CUENTAS), hasItems(primera, segunda, tercera));
    }

    @Entonces("la cuenta {string} muestra saldo {int} y moneda {string}")
    public void laCuentaMuestraSaldoYMoneda(String numero, int saldo, String moneda) {
        verificarQue(TextoDe.el(NUMERO), equalTo(numero));
        verificarQue(TextoDe.el(SALDO), equalTo(String.valueOf(saldo)));
        verificarQue(TextoDe.el(MONEDA), equalTo(moneda));
    }

    @Entonces("la cuenta seleccionada es {string}")
    public void laCuentaSeleccionadaEs(String numero) {
        verificarQue(TextoDe.el(NUMERO), equalTo(numero));
    }

    @Entonces("el saldo de la cuenta es {int}")
    public void elSaldoDeLaCuentaEs(int saldo) {
        verificarQue(TextoDe.el(SALDO), equalTo(String.valueOf(saldo)));
    }

    @Entonces("no se muestra la información de la cuenta")
    public void noSeMuestraLaInformacionDeLaCuenta() {
        verificarQue(ElementoVisible.de(INFORMACION), is(false));
    }

    @Entonces("no puede realizar operaciones sin iniciar sesión de nuevo")
    public void noPuedeRealizarOperaciones() {
        verificarQue(ElementoVisible.de(boton("Deposit")), is(false));
        verificarQue(ElementoVisible.de(boton("Withdrawl")), is(false));
    }

    @Entonces("el campo de monto queda vacío")
    public void elCampoDeMontoQuedaVacio() {
        verificarQue(Campo.valorDe(MONTO), equalTo(""));
    }

    @Entonces("el campo de monto muestra la validación de campo obligatorio")
    public void elCampoDeMontoEsObligatorio() {
        verificarQue(Campo.esObligatorioYEstaVacio(MONTO), is(true));
    }

    @Entonces("el campo de monto muestra la validación de valor no válido")
    public void elCampoDeMontoTieneUnValorNoValido() {
        verificarQue(Campo.tieneUnValorNoValido(MONTO), is(true));
    }

    @Entonces("se muestra un mensaje de error de monto inválido")
    public void seMuestraUnMensajeDeErrorDeMonto() {
        verificarQue(Question.about("si se muestra un mensaje de error de la transacción").answeredBy(actor -> {
            String mensaje = TextoDe.el(MENSAJE).answeredBy(actor);
            return !mensaje.isEmpty() && !mensaje.toLowerCase().contains("successful");
        }), is(true));
    }
}

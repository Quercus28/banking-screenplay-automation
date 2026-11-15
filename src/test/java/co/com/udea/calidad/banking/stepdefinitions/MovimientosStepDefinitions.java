package co.com.udea.calidad.banking.stepdefinitions;

import co.com.udea.calidad.banking.interactions.EsperarSiguienteMinuto;
import co.com.udea.calidad.banking.models.Movimiento;
import co.com.udea.calidad.banking.questions.ElementoVisible;
import co.com.udea.calidad.banking.questions.HistorialDeMovimientos;
import co.com.udea.calidad.banking.questions.Navegacion;
import co.com.udea.calidad.banking.tasks.ConsultarMovimientos;
import co.com.udea.calidad.banking.tasks.FiltrarMovimientos;
import co.com.udea.calidad.banking.tasks.OrdenarListado;
import co.com.udea.calidad.banking.tasks.RealizarTransaccion;
import co.com.udea.calidad.banking.utils.Orden;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Question;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static co.com.udea.calidad.banking.stepdefinitions.Verificacion.actor;
import static co.com.udea.calidad.banking.stepdefinitions.Verificacion.verificarQue;
import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.MENSAJES_DE_ERROR;
import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.boton;
import static co.com.udea.calidad.banking.userinterfaces.PaginaMovimientos.FECHA_FIN;
import static co.com.udea.calidad.banking.userinterfaces.PaginaMovimientos.FECHA_INICIO;
import static co.com.udea.calidad.banking.utils.Constantes.HORA_ULTIMO_RETIRO;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/** Pasos del historial de movimientos (Transactions). */
public class MovimientosStepDefinitions {

    private static final DateTimeFormatter FECHA_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    @Dado("realizó las operaciones")
    public void realizoLasOperaciones(List<Map<String, String>> operaciones) throws InterruptedException {
        for (Map<String, String> operacion : operaciones) {
            String monto = operacion.get("monto");
            actor().wasAbleTo("retiro".equals(operacion.get("tipo"))
                    ? RealizarTransaccion.retiro(monto)
                    : RealizarTransaccion.deposito(monto));
            // Si todas las operaciones caen en el mismo segundo, el filtro de fecha inicial queda por
            // encima de su máximo (truncado a segundos) y la tabla se vacía. Se separan como lo haría un usuario.
            Thread.sleep(1100);
        }
    }

    @Dado("después de un minuto realizó un retiro de {int}")
    public void despuesDeUnMinutoRealizoUnRetiro(int monto) throws InterruptedException {
        actor().wasAbleTo(EsperarSiguienteMinuto.completo(), RealizarTransaccion.retiro(String.valueOf(monto)));
        actor().remember(HORA_ULTIMO_RETIRO, LocalDateTime.now());
        // El límite máximo del filtro es la hora en que se abre la vista, con precisión de segundos:
        // se deja pasar un segundo para que el retiro quede dentro de ese límite.
        Thread.sleep(1500);
    }

    @Cuando("consulta los movimientos de la cuenta")
    public void consultaLosMovimientos() {
        actor().attemptsTo(ConsultarMovimientos.deLaCuenta());
    }

    @Cuando("filtra los movimientos desde {string} hasta {string}")
    public void filtraLosMovimientos(String desde, String hasta) {
        actor().attemptsTo(FiltrarMovimientos.entre(desde, hasta));
    }

    @Cuando("filtra los movimientos desde el minuto del retiro hasta el momento actual")
    public void filtraDesdeElMinutoDelRetiro() {
        LocalDateTime retiro = actor().recall(HORA_ULTIMO_RETIRO);
        String desde = retiro.truncatedTo(ChronoUnit.MINUTES).format(FECHA_HORA);
        String hasta = FECHA_FIN.resolveFor(actor()).getAttribute("max");
        actor().attemptsTo(FiltrarMovimientos.entre(desde, hasta));
    }

    @Cuando("cambia la fecha final del filtro a {int} minutos en el futuro")
    public void cambiaLaFechaFinalAlFuturo(int minutos) {
        actor().attemptsTo(FiltrarMovimientos.hasta(LocalDateTime.now().plusMinutes(minutos).format(FECHA_HORA)));
    }

    @Cuando("ordena los movimientos por {string}")
    public void ordenaLosMovimientos(String columna) {
        actor().attemptsTo(OrdenarListado.porLaColumna(columna));
    }

    @Entonces("el historial muestra los movimientos")
    public void elHistorialMuestraLosMovimientos(List<Map<String, String>> esperados) {
        List<Movimiento> movimientos = esperados.stream()
                .map(fila -> Movimiento.de(fila.get("monto"), fila.get("tipo")))
                .collect(Collectors.toList());
        verificarQue(HistorialDeMovimientos.movimientos(), contains(movimientos.toArray(new Movimiento[0])));
    }

    @Entonces("el historial muestra {int} movimientos")
    public void elHistorialMuestraLaCantidad(int cantidad) {
        verificarQue(HistorialDeMovimientos.cantidad(), equalTo(cantidad));
    }

    @Entonces("el historial no muestra movimientos")
    public void elHistorialNoMuestraMovimientos() {
        verificarQue(HistorialDeMovimientos.cantidad(), equalTo(0));
    }

    @Entonces("se muestran los filtros de fecha y los botones {string} y {string}")
    public void seMuestranLosFiltrosYLosBotones(String primero, String segundo) {
        verificarQue(ElementoVisible.de(FECHA_INICIO), is(true));
        verificarQue(ElementoVisible.de(FECHA_FIN), is(true));
        verificarQue(ElementoVisible.de(boton(primero)), is(true));
        verificarQue(ElementoVisible.de(boton(segundo)), is(true));
    }

    @Entonces("no se muestran los filtros de fecha ni el botón {string}")
    public void noSeMuestranLosFiltrosNiElBoton(String texto) {
        verificarQue(ElementoVisible.de(FECHA_INICIO), is(false));
        verificarQue(ElementoVisible.de(FECHA_FIN), is(false));
        verificarQue(ElementoVisible.de(boton(texto)), is(false));
    }

    @Entonces("se informa que el rango de fechas no es permitido")
    public void seInformaQueElRangoNoEsPermitido() {
        verificarQue(Question.about("si se muestra un mensaje sobre el rango de fechas").answeredBy(actor ->
                MENSAJES_DE_ERROR.resolveAllFor(actor).stream()
                        .anyMatch(elemento -> elemento.isCurrentlyVisible() && !elemento.getText().trim().isEmpty())),
                is(true));
    }

    @Entonces("los movimientos quedan ordenados por fecha de forma {word}")
    public void losMovimientosQuedanOrdenados(String sentido) {
        verificarQue(HistorialDeMovimientos.fechas(),
                "descendente".equals(sentido) ? Orden.<LocalDateTime>descendente() : Orden.<LocalDateTime>ascendente());
    }

    @Entonces("la tabla vuelve al inicio del historial")
    public void laTablaVuelveAlInicio() {
        verificarQue(Navegacion.rutaActual(), endsWith("#anchor"));
    }
}

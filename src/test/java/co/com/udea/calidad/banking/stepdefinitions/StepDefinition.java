package co.com.udea.calidad.banking.stepdefinitions;

import co.com.udea.calidad.banking.interactions.AbrirRuta;
import co.com.udea.calidad.banking.interactions.Navegador;
import co.com.udea.calidad.banking.interactions.PulsarBoton;
import co.com.udea.calidad.banking.questions.ElementoVisible;
import co.com.udea.calidad.banking.questions.Navegacion;
import co.com.udea.calidad.banking.tasks.AbrirBanco;
import co.com.udea.calidad.banking.tasks.IngresarComoAdministrador;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.com.udea.calidad.banking.stepdefinitions.Verificacion.actor;
import static co.com.udea.calidad.banking.stepdefinitions.Verificacion.verificarQue;
import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.boton;
import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.botonQueContiene;
import static co.com.udea.calidad.banking.userinterfaces.PaginaBanco.mensaje;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/** Hooks y pasos comunes: apertura de la aplicación, navegación y elementos visibles. */
public class StepDefinition {

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        // Un único actor cumple los roles de administrador y de cliente: ambos comparten el
        // mismo navegador, que es donde la aplicación guarda sus datos.
        OnStage.theActorCalled("Usuario del banco");
    }

    @Dado("que el visitante abre la aplicación del banco")
    @Dado("que el visitante tiene una sesión nueva del navegador")
    public void abrirLaAplicacionDelBanco() {
        actor().wasAbleTo(AbrirBanco.conDatosIniciales());
    }

    @Cuando("abre directamente la ruta {string}")
    public void abrirDirectamenteLaRuta(String ruta) {
        actor().attemptsTo(AbrirRuta.directamente(ruta));
    }

    @Cuando("ingresa como administrador")
    public void ingresarComoAdministrador() {
        actor().attemptsTo(IngresarComoAdministrador.alBanco());
    }

    @Cuando("pulsa el botón {string}")
    @Cuando("pulsa el control {string}")
    public void pulsarElBoton(String texto) {
        actor().attemptsTo(PulsarBoton.llamado(texto));
    }

    @Cuando("regresa con el botón Atrás del navegador")
    public void regresarConElBotonAtras() {
        actor().attemptsTo(Navegador.regresar());
    }

    @Cuando("recarga la página")
    public void recargarLaPagina() {
        actor().attemptsTo(Navegador.recargar());
    }

    @Entonces("el título de la página es {string}")
    public void elTituloDeLaPaginaEs(String titulo) {
        verificarQue(Navegacion.tituloDeLaPagina(), equalTo(titulo));
    }

    @Entonces("la ruta de la aplicación es {string}")
    public void laRutaDeLaAplicacionEs(String ruta) {
        verificarQue(Navegacion.rutaActual(), equalTo(ruta));
    }

    @Entonces("se muestra el botón {string}")
    public void seMuestraElBoton(String texto) {
        verificarQue(ElementoVisible.de(boton(texto)), is(true));
    }

    @Entonces("se muestran las opciones {string} y {string}")
    @Entonces("se muestran los controles {string} y {string}")
    public void seMuestranLasOpciones(String primera, String segunda) {
        seMuestranLosBotones(primera, segunda);
    }

    @Entonces("se muestran las opciones {string}, {string} y {string}")
    public void seMuestranLasOpciones(String primera, String segunda, String tercera) {
        seMuestranLosBotones(primera, segunda, tercera);
    }

    @Entonces("se muestran las opciones {string}, {string}, {string} y {string}")
    public void seMuestranLasOpciones(String primera, String segunda, String tercera, String cuarta) {
        seMuestranLosBotones(primera, segunda, tercera, cuarta);
    }

    @Entonces("no se muestra el botón {string}")
    public void noSeMuestraElBoton(String texto) {
        noSeMuestranLosBotones(texto);
    }

    @Entonces("no se muestran los botones {string} y {string}")
    public void noSeMuestranLosBotones(String primero, String segundo) {
        noSeMuestranLosBotones(new String[]{primero, segundo});
    }

    @Entonces("no se muestran las opciones {string}, {string} y {string}")
    public void noSeMuestranLasOpciones(String primera, String segunda, String tercera) {
        noSeMuestranLosBotones(primera, segunda, tercera);
    }

    @Entonces("se muestra el control {string} y no se muestran los controles {string} ni {string}")
    public void seMuestraUnControlYNoLosOtros(String visible, String oculto1, String oculto2) {
        seMuestranLosBotones(visible);
        noSeMuestranLosBotones(oculto1, oculto2);
    }

    @Entonces("se muestra el mensaje {string}")
    public void seMuestraElMensaje(String texto) {
        verificarQue(ElementoVisible.de(mensaje(texto)), is(true));
    }

    @Entonces("no se muestra el mensaje {string}")
    public void noSeMuestraElMensaje(String texto) {
        verificarQue(ElementoVisible.de(mensaje(texto)), is(false));
    }

    @Entonces("es redirigido a la pantalla principal o recibe un mensaje de acceso no autorizado")
    public void esRedirigidoOAvisado() {
        verificarQue(Question.about("si la vista protege la ruta").answeredBy(actor -> {
            String ruta = Navegacion.rutaActual().answeredBy(actor);
            String texto = Navegacion.textoVisible().answeredBy(actor).toLowerCase();
            return ruta.equals("#/login") || texto.contains("not authorized") || texto.contains("no autorizado");
        }), is(true));
    }

    @Entonces("no se muestran plantillas sin procesar como {string}")
    public void noSeMuestranPlantillasSinProcesar(String plantilla) {
        verificarQue(Navegacion.textoVisible(), not(containsString(plantilla)));
    }

    @Entonces("se muestra una opción para transferir entre cuentas")
    @Entonces("se muestra una opción relacionada con transferencias")
    public void seMuestraUnaOpcionDeTransferencia() {
        verificarQue(ElementoVisible.de(botonQueContiene("transfer")), is(true));
    }

    private static void seMuestranLosBotones(String... textos) {
        for (String texto : textos) {
            verificarQue(ElementoVisible.de(boton(texto)), is(true));
        }
    }

    private static void noSeMuestranLosBotones(String... textos) {
        for (String texto : textos) {
            verificarQue(ElementoVisible.de(boton(texto)), is(false));
        }
    }
}

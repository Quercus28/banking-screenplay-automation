package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import static co.edu.udea.certificacion.banking.utils.Constantes.URL_BANCO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * La aplicación guarda clientes, cuentas y movimientos en el localStorage del navegador.
 * Vaciarlo y recargar devuelve los datos de demostración, así cada escenario empieza igual.
 */
public class ReiniciarDatosDelBanco implements Interaction {

    private final String ruta;

    public ReiniciarDatosDelBanco(String ruta) {
        this.ruta = ruta;
    }

    public static ReiniciarDatosDelBanco yAbrir(String ruta) {
        return instrumented(ReiniciarDatosDelBanco.class, ruta);
    }

    @Override
    @Step("{0} abre XYZ Bank en '#ruta' con los datos de demostración")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException sinAlertaPendiente) {
            // No había alertas abiertas de un escenario anterior.
        }
        // Se vacía desde un recurso del mismo dominio que no ejecuta la aplicación: si se hiciera
        // con la aplicación cargada, sus guardados diferidos podrían volver a escribir datos.
        driver.get(URL_BANCO + "style.css");
        BrowseTheWeb.as(actor).evaluateJavascript("window.localStorage.clear();");
        driver.get(URL_BANCO + ruta);
    }
}

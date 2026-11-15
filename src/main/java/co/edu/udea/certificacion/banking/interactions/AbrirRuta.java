package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import static co.edu.udea.certificacion.banking.utils.Constantes.URL_BANCO;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Carga una ruta interna desde cero, como si se escribiera la URL en una pestaña nueva. */
public class AbrirRuta implements Interaction {

    private final String ruta;

    public AbrirRuta(String ruta) {
        this.ruta = ruta;
    }

    public static AbrirRuta directamente(String ruta) {
        return instrumented(AbrirRuta.class, ruta);
    }

    @Override
    @Step("{0} abre directamente la ruta '#ruta'")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.get("about:blank");
        driver.get(URL_BANCO + ruta);
    }
}

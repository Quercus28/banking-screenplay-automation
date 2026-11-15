package co.com.udea.calidad.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static co.com.udea.calidad.banking.utils.Constantes.ULTIMA_ALERTA;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Espera brevemente una alerta nativa (window.alert), recuerda su texto y la acepta.
 * Si no aparece ninguna alerta, el actor recuerda un texto vacío.
 */
public class CapturarAlerta implements Interaction {

    private static final Duration ESPERA = Duration.ofSeconds(2);

    public static CapturarAlerta siAparece() {
        return instrumented(CapturarAlerta.class);
    }

    @Override
    @Step("{0} revisa si se muestra una alerta y la acepta")
    public <T extends Actor> void performAs(T actor) {
        String texto = "";
        try {
            Alert alerta = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), ESPERA)
                    .until(ExpectedConditions.alertIsPresent());
            texto = alerta.getText();
            alerta.accept();
        } catch (TimeoutException sinAlerta) {
            texto = "";
        }
        actor.remember(ULTIMA_ALERTA, texto);
    }
}

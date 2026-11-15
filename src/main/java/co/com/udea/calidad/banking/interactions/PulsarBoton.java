package co.com.udea.calidad.banking.interactions;

import co.com.udea.calidad.banking.userinterfaces.PaginaBanco;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Pulsa el primer botón visible con el texto indicado (Home, Logout, Deposit, Back, '>'...). */
public class PulsarBoton implements Interaction {

    private final String texto;

    public PulsarBoton(String texto) {
        this.texto = texto;
    }

    public static PulsarBoton llamado(String texto) {
        return instrumented(PulsarBoton.class, texto);
    }

    @Override
    @Step("{0} pulsa el botón '#texto'")
    public <T extends Actor> void performAs(T actor) {
        WebElementFacade boton = PaginaBanco.boton(texto).resolveAllFor(actor).stream()
                .filter(WebElementFacade::isCurrentlyVisible)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No hay un botón visible con el texto: " + texto));
        boton.click();
    }
}

package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Botones Atrás y Recargar del navegador. */
public class Navegador implements Interaction {

    private final String accion;

    public Navegador(String accion) {
        this.accion = accion;
    }

    public static Navegador regresar() {
        return instrumented(Navegador.class, "atras");
    }

    public static Navegador recargar() {
        return instrumented(Navegador.class, "recargar");
    }

    @Override
    @Step("{0} usa el botón '#accion' del navegador")
    public <T extends Actor> void performAs(T actor) {
        if ("atras".equals(accion)) {
            BrowseTheWeb.as(actor).getDriver().navigate().back();
        } else {
            BrowseTheWeb.as(actor).getDriver().navigate().refresh();
        }
    }
}

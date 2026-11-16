package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Browser Back and Reload buttons. */
public class Browser implements Interaction {

    private final String action;

    public Browser(String action) {
        this.action = action;
    }

    public static Browser goBack() {
        return instrumented(Browser.class, "back");
    }

    public static Browser reload() {
        return instrumented(Browser.class, "reload");
    }

    @Override
    @Step("{0} uses the browser '#action' button")
    public <T extends Actor> void performAs(T actor) {
        if ("back".equals(action)) {
            BrowseTheWeb.as(actor).getDriver().navigate().back();
        } else {
            BrowseTheWeb.as(actor).getDriver().navigate().refresh();
        }
    }
}

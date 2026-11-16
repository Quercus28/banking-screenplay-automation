package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import static co.edu.udea.certificacion.banking.utils.Configuration.bankUrl;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Loads an internal route from scratch, as if the URL were typed in a new tab. */
public class OpenRoute implements Interaction {

    private final String route;

    public OpenRoute(String route) {
        this.route = route;
    }

    public static OpenRoute directly(String route) {
        return instrumented(OpenRoute.class, route);
    }

    @Override
    @Step("{0} opens the route '#route' directly")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.get("about:blank");
        driver.get(bankUrl() + route);
    }
}

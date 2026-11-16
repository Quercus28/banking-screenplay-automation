package co.edu.udea.certificacion.banking.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import static co.edu.udea.certificacion.banking.utils.Configuration.bankUrl;
import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * The application stores customers, accounts and transactions in the browser's localStorage.
 * Clearing it and reloading brings back the demo data, so every scenario starts the same way.
 */
public class ResetBankData implements Interaction {

    private final String route;

    public ResetBankData(String route) {
        this.route = route;
    }

    public static ResetBankData andOpen(String route) {
        return instrumented(ResetBankData.class, route);
    }

    @Override
    @Step("{0} opens XYZ Bank at '#route' with the demo data")
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException noPendingAlert) {
            // No alert was left open by a previous scenario.
        }
        // It is cleared from a resource of the same domain that does not run the application: doing it
        // with the application loaded would let its deferred saves write the data back.
        driver.get(bankUrl() + "style.css");
        BrowseTheWeb.as(actor).evaluateJavascript("window.localStorage.clear();");
        driver.get(bankUrl() + route);
    }
}

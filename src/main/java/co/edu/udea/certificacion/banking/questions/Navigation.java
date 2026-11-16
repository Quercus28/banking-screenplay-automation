package co.edu.udea.certificacion.banking.questions;

import co.edu.udea.certificacion.banking.userinterfaces.BankPage;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.edu.udea.certificacion.banking.utils.Configuration.bankUrl;

/** Data about the current page: route, title and visible text. */
public final class Navigation {

    private Navigation() {
    }

    /** Part of the URL after the base address, for example "#/account". */
    public static Question<String> currentRoute() {
        return Question.about("the current route").answeredBy(actor -> {
            String url = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
            String base = bankUrl();
            return url.startsWith(base) ? url.substring(base.length()) : url;
        });
    }

    public static Question<String> pageTitle() {
        return Question.about("the page title").answeredBy(actor -> BrowseTheWeb.as(actor).getTitle());
    }

    public static Question<String> visibleText() {
        return Question.about("the visible text of the page")
                .answeredBy(actor -> BankPage.BODY.resolveFor(actor).getText());
    }
}

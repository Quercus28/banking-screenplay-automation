package co.com.udea.calidad.banking.questions;

import co.com.udea.calidad.banking.userinterfaces.PaginaBanco;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import static co.com.udea.calidad.banking.utils.Constantes.URL_BANCO;

/** Datos de la página actual: ruta, título y texto visible. */
public final class Navegacion {

    private Navegacion() {
    }

    /** Parte de la URL posterior a la dirección base, por ejemplo "#/account". */
    public static Question<String> rutaActual() {
        return Question.about("la ruta actual").answeredBy(actor -> {
            String url = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
            return url.startsWith(URL_BANCO) ? url.substring(URL_BANCO.length()) : url;
        });
    }

    public static Question<String> tituloDeLaPagina() {
        return Question.about("el título de la página").answeredBy(actor -> BrowseTheWeb.as(actor).getTitle());
    }

    public static Question<String> textoVisible() {
        return Question.about("el texto visible de la página")
                .answeredBy(actor -> PaginaBanco.CUERPO.resolveFor(actor).getText());
    }
}

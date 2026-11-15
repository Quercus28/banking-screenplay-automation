package co.com.udea.calidad.banking.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/** Texto visible de un elemento; vacío si el elemento no está visible. */
public final class TextoDe {

    private TextoDe() {
    }

    public static Question<String> el(Target target) {
        return Question.about("el texto de " + target).answeredBy(actor -> {
            WebElementFacade elemento = target.resolveFor(actor);
            return elemento.isCurrentlyVisible() ? elemento.getText().trim() : "";
        });
    }
}

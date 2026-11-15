package co.edu.udea.certificacion.banking.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/** Indica si al menos uno de los elementos del target está visible. */
public final class ElementoVisible {

    private ElementoVisible() {
    }

    public static Question<Boolean> de(Target target) {
        return Question.about("la visibilidad de " + target).answeredBy(
                actor -> target.resolveAllFor(actor).stream().anyMatch(WebElementFacade::isCurrentlyVisible));
    }
}

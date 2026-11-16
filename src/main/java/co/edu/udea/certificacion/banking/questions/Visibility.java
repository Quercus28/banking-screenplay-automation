package co.edu.udea.certificacion.banking.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/** Tells whether at least one of the target's elements is visible. */
public final class Visibility {

    private Visibility() {
    }

    public static Question<Boolean> of(Target target) {
        return Question.about("the visibility of " + target).answeredBy(
                actor -> target.resolveAllFor(actor).stream().anyMatch(WebElementFacade::isCurrentlyVisible));
    }
}

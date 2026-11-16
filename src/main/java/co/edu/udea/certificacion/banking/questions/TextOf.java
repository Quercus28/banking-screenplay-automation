package co.edu.udea.certificacion.banking.questions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/** Visible text of an element; empty if the element is not visible. */
public final class TextOf {

    private TextOf() {
    }

    public static Question<String> the(Target target) {
        return Question.about("the text of " + target).answeredBy(actor -> {
            WebElementFacade element = target.resolveFor(actor);
            return element.isCurrentlyVisible() ? element.getText().trim() : "";
        });
    }
}

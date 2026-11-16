package co.edu.udea.certificacion.banking.questions;

import net.serenitybdd.screenplay.Question;

import static co.edu.udea.certificacion.banking.utils.Constants.LAST_ALERT;

/** Text of the last native alert; empty if none appeared. */
public final class LastAlert {

    private LastAlert() {
    }

    public static Question<String> text() {
        return Question.about("the text of the last alert").answeredBy(actor -> {
            String text = actor.recall(LAST_ALERT);
            return text == null ? "" : text;
        });
    }
}

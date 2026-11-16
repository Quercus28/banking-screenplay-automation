package co.edu.udea.certificacion.banking.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

/** Options and current selection of a select field. */
public final class Dropdown {

    private Dropdown() {
    }

    public static Question<List<String>> optionsOf(Target dropdown) {
        return Question.about("the options of " + dropdown).answeredBy(actor ->
                new Select(dropdown.resolveFor(actor).getWrappedElement()).getOptions().stream()
                        .map(WebElement::getText)
                        .map(String::trim)
                        .collect(Collectors.toList()));
    }

    public static Question<String> selectedOptionIn(Target dropdown) {
        return Question.about("the option selected in " + dropdown).answeredBy(actor ->
                new Select(dropdown.resolveFor(actor).getWrappedElement()).getFirstSelectedOption().getText().trim());
    }
}

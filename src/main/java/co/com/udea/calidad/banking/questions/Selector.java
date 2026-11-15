package co.com.udea.calidad.banking.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

/** Opciones y selección actual de un campo select. */
public final class Selector {

    private Selector() {
    }

    public static Question<List<String>> opcionesDe(Target selector) {
        return Question.about("las opciones de " + selector).answeredBy(actor ->
                new Select(selector.resolveFor(actor).getWrappedElement()).getOptions().stream()
                        .map(WebElement::getText)
                        .map(String::trim)
                        .collect(Collectors.toList()));
    }

    public static Question<String> opcionSeleccionadaEn(Target selector) {
        return Question.about("la opción seleccionada en " + selector).answeredBy(actor ->
                new Select(selector.resolveFor(actor).getWrappedElement()).getFirstSelectedOption().getText().trim());
    }
}

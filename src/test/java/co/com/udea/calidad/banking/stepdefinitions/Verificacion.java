package co.com.udea.calidad.banking.stepdefinitions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.hamcrest.Matcher;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

/** Verificaciones del actor en escena. Se reintentan unos segundos porque AngularJS actualiza la vista de forma asíncrona. */
final class Verificacion {

    private Verificacion() {
    }

    static Actor actor() {
        return theActorInTheSpotlight();
    }

    static <T> void verificarQue(Question<? extends T> pregunta, Matcher<T> condicion) {
        actor().should(eventually(seeThat(pregunta, condicion)));
    }
}

package co.edu.udea.certificacion.banking.stepdefinitions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.hamcrest.Matcher;

import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

/** Checks made by the actor in the spotlight. They are retried for a few seconds because AngularJS updates the view asynchronously. */
final class Verification {

    private Verification() {
    }

    static Actor actor() {
        return theActorInTheSpotlight();
    }

    static <T> void verifyThat(Question<? extends T> question, Matcher<T> condition) {
        actor().should(eventually(seeThat(question, condition)));
    }
}

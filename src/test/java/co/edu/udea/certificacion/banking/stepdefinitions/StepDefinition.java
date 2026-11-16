package co.edu.udea.certificacion.banking.stepdefinitions;

import co.edu.udea.certificacion.banking.interactions.Browser;
import co.edu.udea.certificacion.banking.interactions.OpenRoute;
import co.edu.udea.certificacion.banking.interactions.PressButton;
import co.edu.udea.certificacion.banking.questions.Navigation;
import co.edu.udea.certificacion.banking.questions.Visibility;
import co.edu.udea.certificacion.banking.tasks.LoginAsBankManager;
import co.edu.udea.certificacion.banking.tasks.OpenBank;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.openqa.selenium.WebDriver;

import static co.edu.udea.certificacion.banking.stepdefinitions.Verification.actor;
import static co.edu.udea.certificacion.banking.stepdefinitions.Verification.verifyThat;
import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.button;
import static co.edu.udea.certificacion.banking.userinterfaces.BankPage.message;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/** Hooks and common steps: opening the application, navigation and visible elements. */
public class StepDefinition {

    @Managed(driver = "chrome", uniqueSession = true)
    WebDriver driver;

    @Before
    public void config() {
        OnStage.setTheStage(new OnlineCast());
        // A single actor plays both the manager and the customer roles: they share the same browser,
        // which is where the application stores its data.
        OnStage.theActorCalled("Bank user").can(BrowseTheWeb.with(driver));
    }

    @Given("the visitor opens the bank application")
    @Given("the visitor has a new browser session")
    public void openTheBankApplication() {
        actor().wasAbleTo(OpenBank.withDemoData());
    }

    @When("the user opens the route {string} directly")
    public void openTheRouteDirectly(String route) {
        actor().attemptsTo(OpenRoute.directly(route));
    }

    @When("the user logs in as bank manager")
    public void logInAsBankManager() {
        actor().attemptsTo(LoginAsBankManager.now());
    }

    @When("the user clicks the {string} button")
    @When("the user clicks the {string} control")
    public void clickTheButton(String text) {
        actor().attemptsTo(PressButton.called(text));
    }

    @When("the user goes back with the browser Back button")
    public void goBackWithTheBrowserBackButton() {
        actor().attemptsTo(Browser.goBack());
    }

    @When("the user reloads the page")
    public void reloadThePage() {
        actor().attemptsTo(Browser.reload());
    }

    @Then("the page title is {string}")
    public void thePageTitleIs(String title) {
        verifyThat(Navigation.pageTitle(), equalTo(title));
    }

    @Then("the application route is {string}")
    public void theApplicationRouteIs(String route) {
        verifyThat(Navigation.currentRoute(), equalTo(route));
    }

    @Then("the {string} button is displayed")
    public void theButtonIsDisplayed(String text) {
        verifyThat(Visibility.of(button(text)), is(true));
    }

    @Then("the {string} and {string} options are displayed")
    @Then("the {string} and {string} controls are displayed")
    public void theOptionsAreDisplayed(String first, String second) {
        buttonsAreDisplayed(first, second);
    }

    @Then("the {string}, {string} and {string} options are displayed")
    public void theOptionsAreDisplayed(String first, String second, String third) {
        buttonsAreDisplayed(first, second, third);
    }

    @Then("the {string}, {string}, {string} and {string} options are displayed")
    public void theOptionsAreDisplayed(String first, String second, String third, String fourth) {
        buttonsAreDisplayed(first, second, third, fourth);
    }

    @Then("the {string} button is not displayed")
    public void theButtonIsNotDisplayed(String text) {
        buttonsAreNotDisplayed(text);
    }

    @Then("the {string} and {string} buttons are not displayed")
    public void theButtonsAreNotDisplayed(String first, String second) {
        buttonsAreNotDisplayed(new String[]{first, second});
    }

    @Then("the {string}, {string} and {string} options are not displayed")
    public void theOptionsAreNotDisplayed(String first, String second, String third) {
        buttonsAreNotDisplayed(first, second, third);
    }

    @Then("the {string} control is displayed but not the {string} and {string} controls")
    public void oneControlIsDisplayedButNotTheOthers(String visible, String hidden1, String hidden2) {
        buttonsAreDisplayed(visible);
        buttonsAreNotDisplayed(hidden1, hidden2);
    }

    @Then("the message {string} is displayed")
    public void theMessageIsDisplayed(String text) {
        verifyThat(Visibility.of(message(text)), is(true));
    }

    @Then("the message {string} is not displayed")
    public void theMessageIsNotDisplayed(String text) {
        verifyThat(Visibility.of(message(text)), is(false));
    }

    @Then("the user is redirected to the home screen or gets an unauthorized access message")
    public void isRedirectedOrWarned() {
        verifyThat(Question.about("whether the view protects the route").answeredBy(actor -> {
            String route = Navigation.currentRoute().answeredBy(actor);
            String text = Navigation.visibleText().answeredBy(actor).toLowerCase();
            return route.equals("#/login") || text.contains("not authorized") || text.contains("unauthorized");
        }), is(true));
    }

    @Then("no unprocessed templates such as {string} are displayed")
    public void noUnprocessedTemplatesAreDisplayed(String template) {
        verifyThat(Navigation.visibleText(), not(containsString(template)));
    }

    private static void buttonsAreDisplayed(String... texts) {
        for (String text : texts) {
            verifyThat(Visibility.of(button(text)), is(true));
        }
    }

    private static void buttonsAreNotDisplayed(String... texts) {
        for (String text : texts) {
            verifyThat(Visibility.of(button(text)), is(false));
        }
    }
}

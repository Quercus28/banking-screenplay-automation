package co.edu.udea.certificacion.banking.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "co.edu.udea.certificacion.banking.stepdefinitions",
        plugin = "io.github.quercus28.serenity.captions.CaptionsPlugin",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class Runner {}

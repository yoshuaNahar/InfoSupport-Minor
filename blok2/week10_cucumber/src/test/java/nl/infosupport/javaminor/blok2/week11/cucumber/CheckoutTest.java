package nl.infosupport.javaminor.blok2.week11.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {"pretty", "html:target/cucumber"},
    features = "src/test/resources/features/checkout.feature",
    glue = "nl.infosupport.javaminor.blok2.week11.cucumber.steps",
    snippets = SnippetType.CAMELCASE)
public class CheckoutTest {

}

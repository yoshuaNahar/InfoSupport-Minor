package nl.infosupport.javaminor.blok2.week11.cucumber.steps;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CheckoutStepsDefinition {

  private int bananaPrice;
  private int applePrice;
  private nl.infosupport.javaminor.blok2.week11.cucumber.implementation.Checkout checkout = new nl.infosupport.javaminor.blok2.week11.cucumber.implementation.Checkout();

  @Given("^the price of a \"([^\"]*)\" is (\\d+)c$")
  public void thePriceOfAIsC(String name, int price) throws Throwable {
    if (name.equals("apple")) {
      applePrice = price;
    } else {
      bananaPrice = price;
    }
  }

  @When("^I checkout (\\d+) \"([^\"]*)\"$")
  public void iCheckout(int itemCount, String itemName) throws Throwable {
    if (itemName.equals("apple")) {
      checkout.add(itemCount, applePrice);
    } else {
      checkout.add(itemCount, bananaPrice);
    }
  }

  @Then("^the total should be (\\d+)c$")
  public void theTotalShouldBeC(int totalPrice) throws Throwable {
    assertThat(checkout.total(), is(totalPrice));
  }

  @Then("^the total price should be (\\d+)c$")
  public void theTotalPriceShouldBeC(int totalPrice) throws Throwable {
    assertThat(checkout.total(), is(totalPrice));
  }

}

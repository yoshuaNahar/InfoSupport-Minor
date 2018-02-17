package nl.infosupport.javaminor.blok2.week11.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WithdrawStepsDefinition {

  @Given("^I have \\$(\\d+) in my account$")
  public void iHave$InMyAccount(int balance) throws Throwable {
    System.out.println("iHave$InMyAccount" + balance);
  }

  @When("^I choose to withdraw the fixed amount of \\$(\\d+)$")
  public void iChooseToWithdrawTheFixedAmountOf$(int withdraw) throws Throwable {
    System.out.println("iChooseToWithdrawTheFixedAmountOf$" + withdraw);
  }

  @Then("^I should receive \\$(\\d+) cash$")
  public void iShouldReceive$Cash(int outcome) throws Throwable {
    System.out.println("iShouldReceive$Cash" + outcome);
  }

  @Then("^the balance of my account should be \\$(\\d+)$")
  public void theBalanceOfMyAccountShouldBe$(int remaining) throws Throwable {
    System.out.println("theBalanceOfMyAccountShouldBe$" + remaining);
  }

}

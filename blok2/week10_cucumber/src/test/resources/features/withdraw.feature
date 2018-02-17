Feature: Withdraw

  Scenario Outline: Withdraw fixed amount
    Given I have <Balance> in my account
    When I choose to withdraw the fixed amount of <Withdrawal>
    Then I should <Outcome>
    And the balance of my account should be <Remaining>

    Examples: Successful withdrawal

    | Balance | Withdrawal | Remaining | Outcome           |
    | $500    | $50        | $450      | receive $50 cash  |
    | $500    | $100       | $400      | receive $100 cash |

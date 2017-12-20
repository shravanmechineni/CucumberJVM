@advil
Feature: Navigation test
  In order to navigate between menu links
  As a PCH behat tester
  I would like to verify navigation menu links

  Scenario: Verify main menu nav
    Given I am on 'https://www.advil.com'
    And I should see main menu nav
    When I navigate to nav links
    Then I should see nav links not broken
 
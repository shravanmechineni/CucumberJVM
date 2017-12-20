@facebook
Feature: As a User, I want to login into a facebook

  Scenario: verify facebook login
    Given I go to facebook login page
    And I enter email 'shravan.scorpio@gmail.com'
    And I enter password '04D61a0442'
    When I click on login
    Then I should logged in

 

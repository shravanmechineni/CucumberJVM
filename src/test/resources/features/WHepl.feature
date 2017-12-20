@EPL
Feature: As a WH Customer, I want the ability to place a bet on a English Premier League event

  Scenario Outline: Verify bet on a English Premier League event
    Given I go to 'https://sports.williamhill.com/sr-admin-set-white-list-cookie.html'
    And I Navigate to a Premiership football event
    And I Select event
    And I place a '<Stake>' bet for the home team to Win
    And I should see the odds and returns offered

    Examples:
      | Stake |
      | 0.05  |
      
 		
 		
      
      

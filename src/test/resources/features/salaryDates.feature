@salrydatesreport
Feature: As a payroll accountant, I can get a report of the dates when the salaries and bonuses need to be paid.

  Scenario Outline: report of the dates when the salaries and bonuses need to be paid
    Given I have month '<Month>' and year '<Year>'
    Then I should see base salary pay date
    And I should see bonus salary pay date

    Examples:
      | Month | Year |
      | 4     | 2017 |
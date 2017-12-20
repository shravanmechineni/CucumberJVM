@sitemap
Feature: Sitemap support
  In order to demonstrate the sitemap.xml available for SEO
  As s developer
  I need toprovide test cases for the sitemap support

  Scenario: check pages in sitemap are available
    Given I have URLs from sitemap
    When I visit individual URL in sitemap
    Then I see the page is available
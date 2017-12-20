package com.cucumbertests.stepdefs.advil;

import com.cucumbertests.pageobjects.NavigationPageObjects;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class NavigationStepDefs {

	private static NavigationPageObjects navPageObj = new NavigationPageObjects();

	@Given("^I am on '(.+)'$")
	public void I_am_on(String url) throws Throwable {
		navPageObj.navigate(url);
	}

	@Given("^I should see main menu nav$")
	public void I_should_see_main_menu_nav() throws Throwable {
		navPageObj.menuLinks();
	}

	@When("^I navigate to nav links$")
	public void I_navigate_to_nav_links() throws Throwable {
		navPageObj.navigateToNavLinks();
	}

	@Then("^I should see nav links not broken$")
	public void I_should_see_nav_links_not_broken() throws Throwable {
		navPageObj.checkNavLinks();
	}

}

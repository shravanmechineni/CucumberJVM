package com.cucumbertests.stepdefs.facebook;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import com.cucumbertests.config.EnvConfig;
import com.cucumbertests.pageobjects.FacebookPageObjects;

import cucumber.api.java8.En;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FacebookStepDefs implements En {

    private FacebookPageObjects fbPageObj = new FacebookPageObjects();

    private static Logger log = LoggerFactory.getLogger(FacebookStepDefs.class);

    public FacebookStepDefs() {

        Given("^I go to facebook login page", () -> {
            String url = EnvConfig.getUrlFor("facebook");
            fbPageObj.navigate(url);
            log.info("navigating to facebook login page");
        });

        Given("^I enter email '(.+)'$", (String email) -> {
            log.debug("email is: " + email);
            fbPageObj.setUsername(email);
        });

        Given("^I enter password '(.+)'$", (String pwd) -> {
            fbPageObj.setPassword(pwd);
        });

        When("^I click on login$", () -> {
            fbPageObj.clickOnLoginButton();
        });

        Then("^I should logged in$", () -> {
            assertThat(fbPageObj.getAccountName(), equalTo("Shravan Rao Mechineni"));
        });
    }
}

package com.cucumbertests.stepdefs.epl;

import com.cucumbertests.pageobjects.EPLPageObjects;
import cucumber.api.java8.En;

public class EPLStepDefs implements En {

    private EPLPageObjects eplPageObj= new EPLPageObjects();


    public EPLStepDefs() {

        Given("^I go to '(.+)'$", (String url) -> {
            eplPageObj.navigate(url);
        });


        Given("^I Navigate to a Premiership football event$", () -> {
            eplPageObj.navigate("https://sports.williamhill.com/betting/en-gb/football");
        });

        Given("^I Select event$", () -> {
            eplPageObj.clickOnEvent();
        });

        Given("^I place a '(.+)' bet for the home team to Win$", (String betValue) ->{
            eplPageObj.clickOnHomeBet();
            eplPageObj.placeBet(betValue);
        });

        Given("^I should see the odds and returns offered$", () -> {

        });
    }
}

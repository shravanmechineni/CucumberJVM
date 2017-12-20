package com.cucumbertests.pageobjects;

import org.openqa.selenium.By;

import com.cucumbertests.util.SharedDriver;

public class EPLPageObjects extends SharedDriver {

    private static final By EVENT_LNK = By.xpath("//div[@id='OB_EV10789957']/div[2]/div[1]/ul/li/a/div/span[1]");
    private static final By HOMEBET_BTN = By.xpath("//button[@id='OB_OU1494129077']");
    private static final By STAKEINPUT_TXT = By.xpath("//input[contains(@class,'betslip-selection__stake-input')]");

    public void clickOnEvent() {
        click(EVENT_LNK);
    }

    public void clickOnHomeBet() {
        waitForElementToBeClickable(HOMEBET_BTN,20);
        click(HOMEBET_BTN);
    }

    public void placeBet(String value) {
        waitForElementPresent(STAKEINPUT_TXT,20);
        sendKeys(STAKEINPUT_TXT, value);
    }
}

package com.cucumbertests.pageobjects;

import org.openqa.selenium.By;

import com.cucumbertests.util.SharedDriver;

public class FacebookPageObjects extends SharedDriver {

    private static final By USERNAME_TXT = By.id("email");
    private static final By PASSWORD_TXT = By.id("pass");
    private static final By LOGIN_BTN = By.xpath("//input[@type='submit']");
    private static final By NAME = By.cssSelector(".linkWrap.noCount");

    public void setUsername(String value) {
        sendKeys(USERNAME_TXT, value);
    }

    public void setPassword(String value) {
        sendKeys(PASSWORD_TXT, value);
    }

    public void clickOnLoginButton() {
        click(LOGIN_BTN);
    }

    public String getAccountName() {
        waitForElementPresent(NAME,10);
        return getText(NAME);
    }
}

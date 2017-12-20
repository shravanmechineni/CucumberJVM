package com.cucumbertests.util;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SharedDriver extends EventFiringWebDriver {

    private static final int DEFAULT_TIMEOUT = 10;
    protected static final WebDriver driver = WebDriverFactory.create();

    public SharedDriver() {
        super(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
    }

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            driver.quit();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    @Override
    public void quit() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't quit this WebDriver. It's shared and will quit when the JVM exits.");
        }
        super.quit();
    }

    public void navigate(String url) {
        driver.navigate().to(url);
    }

    protected void sendKeys(By Element, String value) {
        try {
            driver.findElement(Element).clear();
            driver.findElement(Element).sendKeys(value);
        } catch (NoSuchElementException err) {
            err.printStackTrace();
        }
    }

    protected void click(By element) {
        try {
            driver.findElement(element).click();
        } catch (NoSuchElementException err) {
            err.printStackTrace();
        }
    }

    protected void waitForElementPresent(By element, long sec) {
        (new WebDriverWait(driver, sec)).until(ExpectedConditions.presenceOfElementLocated(element));
    }

    protected void waitForElementToBeClickable(By element, long sec) {
        (new WebDriverWait(driver, sec)).until(ExpectedConditions.elementToBeClickable(element));
    }

    protected String getText(By element) {
        try {
            return driver.findElement(element).getText();
        } catch (Exception e) {
            throw new RuntimeException("Element not found");
        }
    }

    protected boolean isElementPresent(By element) {
        int count = driver.findElements(element).size();
        return count != 0;
    }

    @Before
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (WebDriverException somePlatformsDontSupportScreenshots) {
                System.err.println(somePlatformsDontSupportScreenshots.getMessage());
            }
        }
    }
}




package com.cucumbertests.util;

import static java.lang.String.format;
import static java.lang.System.getProperty;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

final class WebDriverFactory {

    private WebDriverFactory() {
    }

    static WebDriver create() {
        String webDriverProperty = getProperty("webdriver");

        if (webDriverProperty == null || webDriverProperty.isEmpty()) {
            throw new IllegalStateException("The webdriver system property must be set");
        }

        try {
            return Drivers.valueOf(webDriverProperty.toUpperCase()).newDriver();
        } catch (IllegalArgumentException e) {
            String msg = format("The webdriver system property '%s' did not match any " +
                            "existing browser or the browser was not supported on your operating system. " +
                            "Valid values are %s",
                    webDriverProperty, stream(Drivers
                            .values())
                            .map(Enum::name)
                            .map(String::toLowerCase)
                            .collect(toList()));

            throw new IllegalStateException(msg, e);
        }
    }

    private enum Drivers {
        FIREFOX {
            @Override
            public WebDriver newDriver() {
                System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver");
                FirefoxOptions options = new FirefoxOptions();
                return new FirefoxDriver(options);
            }
        }, CHROME {
            @Override
            public WebDriver newDriver() {
                System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--kiosk"); -- to maximize the screen
                return new ChromeDriver(options);
            }
        }, OPERA {
            @Override
            public WebDriver newDriver() {
                OperaOptions options = new OperaOptions();
                return new OperaDriver(options);
            }
        },
//        PHANTOMJS {
//            @Override
//            public WebDriver newDriver() {
//                PhantomJSOptions options = new PhantomJSOptions();
//                return new PhantomJSDriver(options);
//            }
        IE {
            @Override
            public WebDriver newDriver() {
                InternetExplorerOptions options = new InternetExplorerOptions();
                return new InternetExplorerDriver(options);
            }
        }, EDGE {
            @Override
            public WebDriver newDriver() {
                EdgeOptions options = new EdgeOptions();
                return new EdgeDriver(options);
            }
        };

        public abstract org.openqa.selenium.WebDriver newDriver();

    }
}


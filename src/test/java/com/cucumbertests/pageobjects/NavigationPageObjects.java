package com.cucumbertests.pageobjects;

import com.cucumbertests.util.SharedDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class NavigationPageObjects extends SharedDriver {
    private static ArrayList<String> arr = new ArrayList<String>();
    private static Hashtable<String, String> tab = new Hashtable<String, String>();
    private static final By MENU_LINKS = By.cssSelector(".main-nav-item");
    private static final By TITLE_NOT_TXT = By.xpath("//title[not(contains(text(),'Page not found'))]");

    private static Logger log = LoggerFactory.getLogger(NavigationPageObjects.class);

    public void menuLinks() throws Exception {
        Thread.sleep(2000);
        List<WebElement> menuLinks = driver.findElements(By.cssSelector(".main-nav-item"));
        if(menuLinks.isEmpty()) {
            throw new Exception("menuLinks not found");
        }
        log.info(String.valueOf(menuLinks.size()));
//	    Iterator<WebElement> i = menuLinks.iterator();
//	    while (i.hasNext()) {
//	        WebElement item = i.next();
//	        System.out.println(item.getAttribute("href"));
//	        arr.add(item.getAttribute("href"));
//	    }
        for (Iterator<WebElement> i = menuLinks.iterator(); i.hasNext();) {
            WebElement item = i.next();
            arr.add(item.getAttribute("href"));
        }
        log.info(arr.toString());
    }

    public void navigateToNavLinks() throws InterruptedException {
        for(String link: arr) {
            navigate(link);
            Thread.sleep(1000);
            WebElement status =	driver.findElement(By.xpath("//title[not(contains(text(),'Page not found'))]"));
            if(status.getSize() != null) {
                tab.put(link, "Success");
            } else {
                tab.put(link, "Fail");
            }
        }
        log.info(tab.toString());
    }

    public void checkNavLinks () {
        Enumeration<String> links;
        String menuLink;
        String statusMsg;
        links = tab.keys();
        while(links.hasMoreElements()) {
            menuLink = links.nextElement();
            statusMsg = tab.get(menuLink);
            String status = "Success";
            if(statusMsg != "Success") {
                status = "link broken";
            }
            log.info(status + ": " + menuLink);
        }
    }
}

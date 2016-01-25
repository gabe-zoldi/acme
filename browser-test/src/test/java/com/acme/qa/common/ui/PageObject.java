/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.ui;

import com.google.common.base.Function;
import com.acme.qa.common.Global;
import com.acme.qa.common.utilities.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.seleniumhq.selenium.fluent.FluentBy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class PageObject {

    private final int TIMEOUT = 5;     // TimeUnit.SECONDS
    private final int POLL    = 100;   // TimeUnit.MILLISECONDS

    private final WebDriver driver = WebDriverSingletonFactory.getDriver();

    protected WebDriver getDriver() {
        return driver;
    }

    private String failMessage(String reason, String expected) {
        return String.format( "%s [expected] %s", reason, expected );
    }


    protected List<String> flattenByGetText(List<WebElement> list) {
        List<String> flatten = new ArrayList<String>();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            WebElement el = (WebElement) it.next();
            flatten.add(el.getText());
        }
        return flatten;
    }

    protected void waitForTextEquals(WebElement element, final String expected, String reason) {
        new FluentWait<WebElement>( element ).
                withMessage( failMessage(reason, expected) ).
                withTimeout(TIMEOUT, TimeUnit.SECONDS).
                pollingEvery(POLL, TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.getText().equals(expected);
                    }
                });
    }

    protected void waitForTextContains(WebElement element, final String expected, String reason) {
        new FluentWait<WebElement>( element ).
                withMessage( failMessage(reason, expected) ).
                withTimeout(TIMEOUT, TimeUnit.SECONDS).
                pollingEvery(POLL, TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.getText().contains(expected);
                    }
                });
    }

    protected void waitForElementToBeDisplayed(WebElement element, String reason) {
        new FluentWait<WebElement>( element ).
                withMessage( reason ).
                withTimeout(TIMEOUT, TimeUnit.SECONDS).
                pollingEvery(POLL, TimeUnit.MILLISECONDS).
                until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        return element.isDisplayed();
                    }
                });
    }

    protected void waitForelementTextToBePresent(WebElement element, String expected, String reason) {
        // check element text is present
        new WebDriverWait( driver, Global.MAX_TIME_ELEMENT_TO_LOAD ).
                withMessage( reason ).
                until(ExpectedConditions.textToBePresentInElement(element, expected));
    }

    protected void waitForElementClickable(WebElement element, String reason) {
        // check if element is clickable
        new WebDriverWait( driver, Global.MAX_TIME_ELEMENT_TO_LOAD ).
                withMessage( reason ).
                until( ExpectedConditions.elementToBeClickable( element ) );
    }

    protected void clickAllElements(List<WebElement> elements) {
        Iterator it = elements.iterator();
        while ( it.hasNext() ) {
            WebElement element = (WebElement) it.next();
            Utils.snooze(500);
            element.click();
        }
    }

    protected boolean elementExists(WebElement element) {
        try {
            // try to see if selenium can resolve the element
            // if yes, element obviously exists
            // if not, it will throw exception, return false in the catch block
            element.isDisplayed();
            return true;
        }
        catch (NoSuchElementException e) {
            return false;
        }
    }

//    public List<String> convertToListString(List<WebElement> errors) {
//        List<String> list = new ArrayList<String>();
//        Iterator<WebElement> it = errors.iterator();
//
//        while (it.hasNext()) {
//            String error = it.next().getText();
//            list.add(error);
//        }
//
//        return list;
//    }
//
//    public void assertLinkCount(By by, int expectedCount) {
//        List<WebElement> links = driver.findElements( by );
//        assertThat(links.size(), equalTo(expectedCount));
//    }
//
//    public void assertError(List<WebElement> errorList, String expectedError, String testedWithValue) {
//        // converting from List to Set is going to remove duplicate web elements
//        // refactor if expected to see duplicates
//        Set<WebElement> converted = new HashSet<WebElement>( errorList );
//
//        // build list of errors displayed on the page (removing dupes seen)
//        Set<String> errorsSeenOnThePage = new HashSet<String>();
//        Iterator<WebElement> it = converted.iterator();
//        while (it.hasNext())
//            errorsSeenOnThePage.add( it.next().getText() );
//
//        // verify expected error is in the list of actual errors seen on the page
//        String reason = "";
//        if (!testedWithValue.isEmpty())
//            reason = "Did not get any errors trying '" + testedWithValue + "'";
//
//        assertThat(reason, errorsSeenOnThePage, contains(expectedError));
//    }
//
//    public void assertNoError(List<WebElement> errorList) {
//        int found = errorList.size();
//        if ( found == 0 ) {
//            return;
//        }
//
//        // unexpected error was found on the page
//        Iterator<WebElement> it = errorList.iterator();
//        StringBuilder errors = new StringBuilder();
//        while (it.hasNext())
//            errors.append( it.next().getText() + "\n");
//
//        String reason = "Found unexpected error: " + errors;
//        Assert.fail(reason);
//    }

    public static By ngWait(final By by) {
        return new FluentBy() {
            @Override
            public void beforeFindElement(WebDriver driver) {
                driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

                ((JavascriptExecutor) driver).
                        executeAsyncScript(
                                "var callback = arguments[arguments.length - 1];" +
                                        "angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);"
                        );

                super.beforeFindElement(driver);
            }

            @Override
            public WebElement findElement(SearchContext context) {
                return by.findElement(context);
            }

            @Override
            public List<WebElement> findElements(SearchContext context) {
                return by.findElements(context);
            }

            @Override
            public String toString() {
                return "ngWait(" + by.toString() + ")";
            }
        };
    }
}
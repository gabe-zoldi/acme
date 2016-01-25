/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.model.ui.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.acme.qa.common.ui.PageObject;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class Cart extends PageObject {

    private final WebDriver driver;

    public Cart() {
        driver = getDriver();
    }

    public static class OrderSummary {
        public static final String TITLE = "Order Summary";
    }

    /*
     * web controls
     */
    public WebElement text_OrderSummaryHeading() {
        return driver.findElement(By.cssSelector("h2.order-summary-heading"));
    }

    public WebElement text_OrderSummaryItemCount() {
        return driver.findElement(By.cssSelector("div.cart-num.accordion-heading p"));
    }

    public List<WebElement> textlist_CartItems() {
        return driver.findElements(By.cssSelector("div[class='span6 ng-binding']:not([sp-delivery-date=''])"));
    }

    /*
     * assertions
     */
    public void assertOrderSummaryContains(String... expectedList) {
        List<String> actual = flattenByGetText(textlist_CartItems());
        for (String expected : expectedList)
            assertThat(actual, hasItem(expected));
    }

}

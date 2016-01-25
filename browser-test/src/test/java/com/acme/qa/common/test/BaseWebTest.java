/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.test;

import org.testng.Assert;
import com.acme.qa.common.ui.PageObject;
import com.acme.qa.common.ui.WebDriverSingletonFactory;
import com.acme.qa.site.bluntusa.model.data.Product;
import com.acme.qa.site.bluntusa.model.ui.checkout.Cart;
import com.acme.qa.site.bluntusa.model.ui.Home;
import org.openqa.selenium.WebDriver;
import com.acme.qa.site.bluntusa.model.ui.NavBar;
import com.acme.qa.site.bluntusa.model.ui.product.Umbrellas;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class BaseWebTest extends PageObject {

    private final WebDriver driver;

    public BaseWebTest() {
        driver = getDriver();
    }

    /*
     * super keywords (super high-level)
     */
    protected void open() {
        new Home().open();
    }

    protected void close() {
        WebDriverSingletonFactory.stopEverything();
    }

    protected void addToCart(Product product) {
        if (product.getType().equals(Product.Type.UMBRELLA)) {
            new Umbrellas().addToCart(product);
        }
        else {
            Assert.fail("Could not determine context of the page object.");
        }
    }

    // TODO: need to refactor test code to allow checking for multiple items in cart
    protected void verifyCartContains(List<Product> products) {
        for (Product product : products) {
            verifyCartContains(product);
        }
    }

    protected void verifyCartContains(final Product product) {

        // goto checkout
        new NavBar().img_Cart().click();

        // verify correct items are in the cart
        new Cart() {{
            waitForElementToBeDisplayed(
                    text_OrderSummaryHeading(),
                    "Order Summary was not found in the Cart."
            );

            // verify correct number of items
            // TODO: this checks for 1 item only, refactor to check for items > 1 based on list count
            assertThat(
                    text_OrderSummaryItemCount().getText(),
                    containsString("1 items")
                    //containsString("1 itemsXXXXXX")  // <== this obviously should fail since expected is wrong
            );

            // verify products added is in the order summary list
            assertOrderSummaryContains(product.getTitle());
        }};
    }

}

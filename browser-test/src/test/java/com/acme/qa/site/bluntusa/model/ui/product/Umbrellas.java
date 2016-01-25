/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.model.ui.product;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import com.acme.qa.common.ui.PageObject;
import org.openqa.selenium.WebElement;
import com.acme.qa.site.bluntusa.model.data.Product;
import java.util.List;
import java.util.ListIterator;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class Umbrellas extends PageObject {

    private final WebDriver driver;

    public Umbrellas() {
        driver = getDriver();
    }

    /*
     * web controls
     */
    public List<WebElement> linklist_Products() {
        return driver.findElements(By.cssSelector("ul.product-list img.product"));
    }

    /*
     * keywords
     */
    public void addToCart(Product product) {

        // find and click product from the product list (shown with images)
        int index = getProductIndex(product);
        linklist_Products().get(index).click();

        // click Add to Cart button
        new Description() {{
            button_AddToCart().click();

            // clicking add to cart button changes the text to 'Adding...'
            // when it completes it switches back to 'Add to cart'
            new WebDriverWait(driver, 10).
                    until( ExpectedConditions.textToBePresentInElement(button_AddToCart(), "add to cart") );
        }};
    }

    private int getProductIndex(Product product) {
        return getIndex(product, linklist_Products());
    }

    private int getIndex(Product product, List<WebElement> elements) {

        ListIterator lit = elements.listIterator();
        while( lit.hasNext() ) {
            WebElement row = (WebElement) lit.next();
            String displayName = row.getAttribute("title");
            if ( displayName.contains( product.getTitle() ) )
                return lit.nextIndex() - 1;
        }

        // fail test if drug was not found in the list
        Assert.fail("Could not find product in the product list named: " + product);
        return -1;
    }

}
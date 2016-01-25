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
import com.acme.qa.common.ui.PageObject;
import org.openqa.selenium.WebElement;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class Description extends PageObject {

    private final WebDriver driver;

    public Description() {
        driver = getDriver();
    }

    /*
     * web controls
     */
    public WebElement button_AddToCart() {
        WebElement element = driver.findElement(By.cssSelector("button.add-to-cart.ng-scope"));
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

}

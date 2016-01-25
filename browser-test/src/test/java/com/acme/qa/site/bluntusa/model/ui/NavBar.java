/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.model.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.acme.qa.common.ui.PageObject;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class NavBar extends PageObject {

    private final WebDriver driver;

    public NavBar() {
        driver = getDriver();
    }

    /*
     * web controls
     */
    public WebElement dropdown_Products() {
        return driver.findElement(By.cssSelector("span[ng-click='toggleDropdownMenu()']"));
    }

    public WebElement link_UmbrellaRange() {
        return driver.findElement(By.cssSelector("a[href='/all']"));
    }

    public WebElement img_Cart() {
        return driver.findElement(By.cssSelector("img.cart-mini"));
    }

    /*
     * keywords
     */
    public void gotoUmbrellas() {
        dropdown_Products().click();
        link_UmbrellaRange().click();
    }

}

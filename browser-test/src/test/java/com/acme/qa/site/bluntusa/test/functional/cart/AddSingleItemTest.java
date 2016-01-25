/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.test.functional.cart;

import com.acme.qa.common.test.BaseWebTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.acme.qa.common.utilities.Utils;
import com.acme.qa.site.bluntusa.model.data.Product;
import com.acme.qa.site.bluntusa.model.ui.NavBar;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class AddSingleItemTest extends BaseWebTest {

    @BeforeClass
    public void setUp() {
        // launch browser
        open();
        new NavBar().gotoUmbrellas();
    }

    @AfterClass
    public void tearDown() {
        // exit browser
        close();
    }

    //@Test(groups = { TestType.END_TO_END, TestType.ACCEPTANCE }, dependsOnGroups = TestType.SMOKE)
    @Test
    public void addToCart_SomeRandomUmbrella() {

        Product product = Product.BLUNT_XS_METRO_UMBRELLA;
        addToCart(product);
        verifyCartContains(product);
        //verifyCartContains(Product.FOOBAR);  // <== this obviously should fail since expected is wrong



        // purposely adding delay to see the end which happens too fast to see
        Utils.snooze(5000);
    }

}

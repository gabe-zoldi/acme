/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.model.ui;

        import com.acme.qa.common.Config;
        import org.openqa.selenium.WebDriver;
        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.MatcherAssert.assertThat;
        import com.acme.qa.common.ui.PageObject;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class Home extends PageObject {

    private final WebDriver driver;

    public Home() {
        driver = getDriver();
    }

    public static class Expected {
        // success
        public static final String TITLE = "Blunt Umbrellas | The World's Best Umbrellas - designed by New Zealand";
        public static final String LOGGED_OUT_SUCCESS = "You have been successfully logged out.";
        // informational
        public static final String LOGGED_OUT_INACTIVITY = "For the safety of your data, we have logged you out due to inactivity.";
        // error
        public static final String EMAIL_PASSWORD_DO_NOT_MATCH = "Email and password do not match.";
    }

    /*
     * keywords
     */
    public void open() {
        // open browser to starting url
        driver.get( Config.getProperty("app.url") );
        // check page title
        assertThat( driver.getTitle(), equalTo( Expected.TITLE ) );
    }

}

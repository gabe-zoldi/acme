/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.ui;

import com.acme.qa.common.Config;
import com.acme.qa.common.framework.PropertyManager;
import com.acme.qa.common.Global;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.acme.qa.common.type.browser.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public abstract class WebDriverSingletonFactory {

    private static WebDriver driver;

    // read from config.properties
    private final static BrowserOS
            BROWSER_OS = BrowserOS.getBrowserOS( Config.getProperty("browser.os") );

    private final static BrowserType
            BROWSER_TYPE = BrowserType.getBrowserType( Config.getProperty( "browser.type" ) );

    private final static String
            BROWSER_VERSION = Config.getProperty( "browser.version" );

    // build object mapping for browser type and version
    private final static BrowserVersion
            BROWSER_TYPE_AND_VERSION = BrowserVersion.getBrowser( BROWSER_TYPE, BROWSER_VERSION );

    public static WebDriver getDriver() {
        // singleton
        if (driver == null)
            createDriver();
        return driver;
    }

    private static DesiredCapabilities getCapabilities(BrowserOS browserOS, BrowserVersion browserVersion) {
        DesiredCapabilities caps = Browser.getDesiredCapabilities(browserOS, browserVersion);
        return caps;
    }

    private static DesiredCapabilities getCapabilities(DeviceVersion deviceVersion) {
        DesiredCapabilities caps = Browser.getDesiredCapabilities(deviceVersion);
        return caps;
    }

    private static void createDriver() {
        // check if tests should run locally on dev box or on remote selenium grid testng environment
        boolean runTestsInSeleniumGrid =
                Boolean.parseBoolean( Config.getProperty("seleniumgrid.runtests") );

        // if asking to run in grid create remote webdriver w/ capabilities
        // else create and set with browser specific local webdriver (firefox, chrome, etc)
        driver = ( runTestsInSeleniumGrid ) ?
                createDriverForRemoteExecution() :   // selenium grid mode: tests sent to remote node to be executed
                createDriverForLocalExecution();     // dev or local mode: tests live and executed locally

        // set how long to wait for elements. etc.
        driver.manage().timeouts().implicitlyWait( Global.MAX_TIME_ELEMENT_TO_LOAD, TimeUnit.SECONDS );

        // make it big as possible so taking screenshot doesn't miss anything
        //if (Config.getProperty("screenshot.on.failure").equals("true")) {
        //    driver.manage().window().maximize();
        //}

    }

    /*
     * create Remote WebDriver
     */
    private static WebDriver createDriverForRemoteExecution() {
        // check if valid browser os specified
        if ( BROWSER_OS == null )
            throw new RuntimeException(
                    String.format(
                            "BrowserOS specified (%s) not found: browser.os=%s",
                            PropertyManager.CONFIG_PROP,
                            Config.getProperty( "browser.os" )
                    )
            );

        // check if valid browser type and version specified
        if ( BROWSER_TYPE_AND_VERSION == null )
            throw new RuntimeException( String.format(
                    "Browser specified (%s) not found: browser.type=%s, browser.version=%s  [ Please refer to %s ]",
                    PropertyManager.CONFIG_PROP,
                    Config.getProperty( "browser.type" ),
                    Config.getProperty( "browser.version" ),
                    BROWSER_TYPE_AND_VERSION.getClass().getName()
            ));


        // assumptions: please ensure that selenium grid is always 'Running' as a service
        // UnreachableBrowserException or HttpHostConnectException: Connection refused may occur
        String seleniumGridHubUrl = String.format(
                "http://%s:%s/wd/hub",
                Config.getProperty( "seleniumgrid.host" ),
                Config.getProperty( "seleniumgrid.port" )
        );

        // create remote driver and let hub decide from capabilities
        // where to send off to slave node for testing
        try {
            return new RemoteWebDriver(
                    new URL( seleniumGridHubUrl ),
                    getCapabilities( BROWSER_OS, BROWSER_TYPE_AND_VERSION )
            );
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Invalid hub connect url for selenium grid: " + seleniumGridHubUrl, e);
        }
    }

    /*
     * create Local WebDriver
     */
    private static WebDriver createDriverForLocalExecution() {
        // TODO: add check if system.property set like '-Dbrowser.type=firefox' to override default value
        // check if valid browser type specified
        if ( BROWSER_TYPE == null )
            throw new RuntimeException( String.format(
                    "BrowserType specified (%s) not found: browser.type=%s",
                    PropertyManager.CONFIG_PROP,
                    Config.getProperty( "browser.type" )
            ));

        // create webdriver for the browser type
        if ( BROWSER_TYPE.equals( BrowserType.CHROME ) ) {
            String property = "webdriver.chrome.driver";
            File file = new File( Config.getProperty( property ) );
            System.setProperty( property, file.getAbsolutePath() );
            return new ChromeDriver();
        }
        else if ( BROWSER_TYPE.equals(BrowserType.IE) ) {
            String property = "webdriver.ie.driver";
            File file = new File( Config.getProperty( property ) );
            System.setProperty( property, file.getAbsolutePath() );
            return new InternetExplorerDriver();
        }
        else if ( BROWSER_TYPE.equals(BrowserType.FIREFOX) ) {
            return new FirefoxDriver();
        }

        throw new RuntimeException( "Matching BrowserType not found: " + BROWSER_TYPE );
    }

    public static void stopEverything() {
        // TODO: add any needed cleanup code here: reports, error checks, db rollbacks, etc.
        driver.quit();
    }

}

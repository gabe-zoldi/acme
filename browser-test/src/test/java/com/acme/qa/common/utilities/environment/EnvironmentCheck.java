/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.utilities.environment;

import com.acme.qa.common.framework.logging.Log;
import java.net.URL;
import java.net.URLConnection;
import java.io.InputStream;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class EnvironmentCheck {

    /**
     * Verifies URL returns a response
     * @param 	url
     * @return  bool
     */
    public static boolean verifyURL(String url) {

        boolean connectionValid = false;
        URL testURL = null;
        URLConnection conn = null;
        InputStream input = null;

        try {
            testURL = new URL( url );

            // TODO: add to check 200 response code
            // TODO: look at integrating http://developer.eclipsesource.com/restfuse/
            if ( testURL != null ) {
                conn = testURL.openConnection();
                input = conn.getInputStream();
                input.close();
                connectionValid = true;
            }
        }
        catch (Exception e) {
            Log.error( "Exception verifying URL: " + url + "\n" + e.getMessage() );
        }

        return connectionValid;
    }
}

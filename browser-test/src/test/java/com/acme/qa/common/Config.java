/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common;

import com.acme.qa.common.framework.PropertyManager;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class Config {

    public static String getProperty(String key) {
        // using Apache Commons Configuration for variable interpolation
        try {
            Configuration config = new PropertiesConfiguration( PropertyManager.CONFIG_PROP );
            String value = config.getString(key);
            Config.validateProp(key, value);
            return value;
        }
        catch (ConfigurationException e) {
            throw new RuntimeException(
                    String.format( "Error getting value for the key '%s' from the property file %s", key, PropertyManager.CONFIG_PROP ),
                    e);
        }
    }

    public static void validateProp(String key, String value) {
        // check if property is undefined
        if ( value == null )
            throw new RuntimeException(
                    String.format( "The key '%s' is missing in the %s.", key, PropertyManager.CONFIG_PROP )
            );

        // check if property is unset
        if ( value.isEmpty() )
            throw new RuntimeException(
                    String.format( "The key '%s' is unset in the %s.", key, PropertyManager.CONFIG_PROP )
            );
    }

}

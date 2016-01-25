/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.framework.exception;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class ConfigurationRuntimeException extends RuntimeException {

    /***
     * Constructor to print the configuration runtime exception
     * @param message
     */
    public ConfigurationRuntimeException(String message) {
        super(message);
    }

}

/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.framework.exception;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class PropertyManagerRuntimeException extends RuntimeException {

    /***
     * Constructor to print the property manager runtime exception
     * @param message
     */
    public PropertyManagerRuntimeException(String message) {
        super(message);
    }

}

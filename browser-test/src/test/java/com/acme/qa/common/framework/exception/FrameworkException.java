/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.framework.exception;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class FrameworkException extends Exception {

    /***
     * Constructor to print the framework exception
     * @param message
     */
    public FrameworkException(String message) {
        super(message);
    }

}

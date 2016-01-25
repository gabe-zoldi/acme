/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.framework;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public enum MachineType {

    FE ("frontend"),
    BE ("backend"),
    DB ("database"),

    PORTAL ("portal");

    private final String keyName;

    MachineType(String key) {
        keyName = key;
    }

    public String getKey() {
        return keyName;
    }
}

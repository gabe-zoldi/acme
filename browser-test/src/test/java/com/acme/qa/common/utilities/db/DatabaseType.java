/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.utilities.db;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public enum DatabaseType {

    MYSQL    ("mysql"),
    ORACLE   ("oracle"),
    MSSQL    ("mssql"),
    POSTGRES ("postgres"),

    QA    ("qa"),
    STAGE ("stage");

    private String type;

    DatabaseType(String type) {
        this.type = type;
    }

    public String getDatabaseType() {
        return type;
    }
}
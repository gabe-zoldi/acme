/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.utilities.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public interface Database {

    public ArrayList<Map<String, String>> executeQuery(String query);

    public int executeUpdate(String query);

    public ResultSet getResultSet(String query);

    public String getDriver();
    public String getUrl();
    public String getHost();
    public String getPort();
    public String getUser();
    public String getPassword();
    public String getName();
}

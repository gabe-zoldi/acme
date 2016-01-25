/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.utilities.db.types.base;

import com.acme.qa.common.framework.ConfigurationManager;
import com.acme.qa.common.utilities.db.DBConnection;
import com.acme.qa.common.utilities.db.Database;
import com.acme.qa.common.utilities.db.DatabaseType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class PostgresDatabase extends DBConnection implements Database {

    public ArrayList<Map<String, String>> executeQuery(String query) {
        setConnection();
        return executeDDL(query);
    }

    public int executeUpdate(String query) {
        setConnection();
        return executeDML(query);
    }

    public ResultSet getResultSet(String query) {
        setConnection();
        return executeResultSet(query);
    }

    private DatabaseType dbType = DatabaseType.POSTGRES;

    public String getDriver() {
        return ConfigurationManager.getDatabaseDriver(dbType);
    }

    public String getUrl() {
        return ConfigurationManager.getDatabaseUrl( dbType );
    }

    public String getHost() {
        return ConfigurationManager.getDatabaseHost( dbType );
    }

    public String getPort() {
        return ConfigurationManager.getDatabasePort(dbType);
    }

    public String getUser() {
        return ConfigurationManager.getDatabaseUser( dbType );
    }

    public String getPassword() {
        return ConfigurationManager.getDatabasePassword( dbType );
    }

    public String getName() {
        return ConfigurationManager.getDatabaseName( dbType );
    }

}

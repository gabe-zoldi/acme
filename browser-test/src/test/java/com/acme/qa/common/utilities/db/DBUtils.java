/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.utilities.db;

import com.acme.qa.common.framework.PropertyManager;
import com.acme.qa.common.framework.logging.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class DBUtils {

    private static Connection conn;

    /**
     * This method gets a database connection using the configuration info
     *
     * @param serverUrl
     * @param username
     * @param password
     * @return
     */
    @Deprecated
    private static void connectOracle(String serverUrl, String username, String password) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" + serverUrl, username, password);
            Log.info("Connected to : " + serverUrl);
        }
        catch (Exception e) {
            throw new RuntimeException(
                    "Unable to create connection..serverUrl: " + serverUrl
                            + ", user:" + username
                            + ", password:" + password, e
            );
        }
    }

    /**
     * This method returns a map after connecting to the database and executing
     * the query Before Executing this method connectOracle or connectSybase has
     * to be executed
     *
     * @param query
     * @return java.util.Map<String, String>
     */
    @Deprecated
    public static ArrayList<Map<String, String>> ORAExecuteQueryAndGetListMap(String query) {

        connectOracle(PropertyManager.get("oradb_server_url"), PropertyManager.get("oradb_user"), PropertyManager.get("oradb_password"));
        ArrayList<Map<String, String>> myList = new ArrayList<Map<String, String>>();
        ResultSet rs = null;

        try {
            rs = getResultSetInstance(conn, query);
            if (rs == null) return null;

            while (rs.next()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumns = rsmd.getColumnCount();
                Map<String, String> map = new HashMap<String, String>();

                // Get the column names; column indices start from 1
                for (int i = 1; i < numColumns + 1; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = rs.getString(columnName);
                    map.put(columnName, columnValue);
                }

                myList.add(map);
            }

            rs.close();
            conn.close();
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to get data for execution", e);
        }

        return myList;
    }

    /**
     * This method gets the instance of the result Set
     *
     * @param conn
     * @param sql
     * @return java.sql.ResultSet
     */
    @Deprecated
    private static ResultSet getResultSetInstance(Connection conn, String sql) {

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            throw new RuntimeException("Could not execute query:" + sql, e);
        }

        return rs;
    }

    /**
     * This method gets the instance of the result Set
     *
     * @param sql
     * @return java.sql.ResultSet
     */
    @Deprecated
    public static ResultSet ORAGetResultSet(String sql) {

        connectOracle(PropertyManager.get("oradb_server_url"), PropertyManager.get("oradb_user"), PropertyManager.get("oradb_password"));
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        }
        catch (SQLException e) {
            throw new RuntimeException("Could not execute query:" + sql, e);
        }
        return rs;
    }


    /**
     * This method returns a map after connecting to the oracle database and executing
     * the query
     *
     * @param query
     * @return java.util.Map<String, String>
     */
    @Deprecated
    public static Map<String, String> ORAExecuteQueryAndGetMap(String query) {

        connectOracle(PropertyManager.get("oradb_server_url"), PropertyManager.get("oradb_user"), PropertyManager.get("oradb_password"));
        Map<String, String> map = new HashMap<String, String>();
        ResultSet rs = null;

        try {
            rs = getResultSetInstance(conn, query);
            if (rs == null) return null;

            while (rs.next()) {

                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumns = rsmd.getColumnCount();

                // get the column names; column indices start from 1
                for (int i = 1; i < numColumns + 1; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = rs.getString(columnName);
                    Log.debug("COLUMN NAME: " + columnName + ", " + "COLUMN VALUE: " + columnValue);
                    map.put(columnName, columnValue);
                }
            }

            rs.close();
            conn.close();
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to get data for execution", e);
        }

        return map;
    }

    /**
     * This method returns a integer after connecting to the database and executing
     * the query
     *
     * @param query
     * @return int
     */
    @Deprecated
    public static int ORAExecuteUpdate(String query) {

        int numRows=0;
        connectOracle(PropertyManager.get("oradb_server_url"), PropertyManager.get("oradb_user"), PropertyManager.get("oradb_password"));
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            numRows = stmt.executeUpdate(query);
        }
        catch (SQLException e) {
            throw new RuntimeException("Could not execute SQL statement:" + query, e);
        }

        return numRows;
    }

    public static ArrayList<Map<String, String>> getListMapFromResultSet(ResultSet rs) {

        ArrayList<Map<String, String>> myList = new ArrayList<Map<String, String>>();

        try {
            while (rs.next()) {
                Map<String, String> map = new HashMap<String, String>();
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumns = rsmd.getColumnCount();

                // get the column names; column indices start from 1
                for (int i = 1; i < numColumns + 1; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = rs.getString(columnName);
                    map.put(columnName, columnValue);
                }

                myList.add(map);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Unable to get data for execution", e);
        }

        return myList;
    }

}

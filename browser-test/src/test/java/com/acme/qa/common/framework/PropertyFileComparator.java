/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.framework;

import java.util.Comparator;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class PropertyFileComparator implements Comparator<PropertyFile> {

    public int compare(PropertyFile propFile1, PropertyFile propFile2) {
        return propFile1.getPriority() - propFile2.getPriority();
    }
}

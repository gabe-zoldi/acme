/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.utilities;

import java.util.Comparator;
import java.util.Map;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class MapKeySorter implements Comparator<Map<String, String>> {

    String sortMapKey = null;

    public MapKeySorter(String inMapKey) {
        super();
        this.sortMapKey = inMapKey;
    }

    public int compare(Map<String, String> o1, Map<String, String> o2) {
        // these conditionals handle maps that don't have the key value
        // so a header row map will remain at the top of the list
        if ( !o1.containsKey(sortMapKey) && o2.containsKey(sortMapKey) ) {
            return -1;
        }
        else if ( o1.containsKey(sortMapKey) && !o2.containsKey(sortMapKey) ) {
            return 1;
        }
        else if ( !o1.containsKey(sortMapKey) && !o2.containsKey(sortMapKey) ) {
            return 0;
        }
        // both maps have the key, compare using that key
        else {
            return o1.get(sortMapKey).compareTo(o2.get(sortMapKey));
        }
    }

}

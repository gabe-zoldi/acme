/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.utilities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class SearchListMap {

    @SuppressWarnings("unchecked")
    public static <T extends Map<String, String>> T
    findByKeyMap(List<? extends Map<String, String>> inListMap, Map<String, String> inKeyMap, Map<String, String> inValuesMap) {

        boolean found = false;
        T returnMap = null;

        for (Map<String, String> someMap : inListMap) {

            for (String key1 : inKeyMap.keySet()) {
                String testValue1 = someMap.get(key1);
                String key2 = inKeyMap.get(key1);
                String testValue2 = inValuesMap.get(key2);

                if (testValue1==null || testValue2==null) {
                    found = false;
                    break;
                }

                if (testValue1.equals(testValue2)) {
                    found = true;
                }
                else {
                    found = false;
                    break;
                }
            }

            if ( found ) {
                returnMap = (T) someMap;
                break;
            }
        }

        return returnMap;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Map<String, String>> T
    findByMap(List<? extends Map<String, String>> inListMap, Map<String, String> inValuesMap) {

        // Set up a reflexive (a=a) KeyMap
        Map<String, String> inKeyMap = new HashMap<String, String>();
        for (String someKey : inValuesMap.keySet())
            inKeyMap.put(someKey, someKey);

        // call more complex version using reflexive KeyMap
        return (T) findByKeyMap(inListMap, inKeyMap, inValuesMap);
    }

}

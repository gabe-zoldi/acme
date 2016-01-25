/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.utilities;

import com.acme.qa.common.framework.logging.Log;
import java.lang.reflect.Method;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public class ReflectionUtility {

    public static Object invokeMethod(String callingClass, String methodName) {
        Class<?> reflectedClass = null;
        Object returnValue = null;

        try {
            reflectedClass = Class.forName(callingClass);

            // if null no point in executing subsequent code
            if (reflectedClass == null ) return null;

            Method method = reflectedClass.getMethod(methodName);
            Object retobj = reflectedClass.newInstance();
            returnValue = method.invoke(retobj);
        }
        catch (ClassNotFoundException e) {
            Log.error("Cannot find class [" + callingClass + "] for reflection - " + e.getMessage());
        }
        catch (NoSuchMethodException e) {
            Log.error("Cannot find method [" + methodName + "] in class [" + callingClass + "] for reflection - " + e.getMessage());
        }
        catch (Exception e) {
            Log.error("Error in loading [" + callingClass + "] for reflection - " + e.getMessage());
        }

        return returnValue;
    }

}

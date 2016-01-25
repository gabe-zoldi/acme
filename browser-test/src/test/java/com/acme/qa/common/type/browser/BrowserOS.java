/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.common.type.browser;

import java.util.*;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public enum BrowserOS {

    // this list was created from saucelabs compiled list of OSes they support
    WINDOWS_XP  ("Windows XP",  "Whistler"),
    WINDOWS_7   ("Windows 7",   "Blackcomb"),
    WINDOWS_8   ("Windows 8",   "Jupiter"),
    WINDOWS_8_1 ("Windows 8.1", "Blue"),

    MAC_OS_X_10_6 ("OS X 10.6", "Snow Leopard"),
    MAC_OS_X_10_8 ("OS X 10.8", "Mountain Lion"),
    MAC_OS_X_10_9 ("OS X 10.9", "Mavericks"),

    LINUX ("Linux", "Generic");

    private String name;
    private String codename;

    /**
     * A mapping between the platform and its corresponding OS to facilitate lookup by code.
     */
    private static Map<String, BrowserOS> nameToOSMapping;

    private BrowserOS(String name, String codename) {
        this.name = name;
        this.codename = codename;
    }

    public static BrowserOS getBrowserOS(String name) {
        if (nameToOSMapping == null) {
            initMapping();
        }
        return nameToOSMapping.get(name);
    }

    private static void initMapping() {
        nameToOSMapping = new HashMap<String, BrowserOS>();
        for (BrowserOS os : values()) {
            nameToOSMapping.put(os.name, os);
        }
    }

    public String getName() {
        return name;
    }

    public String getCodename() {
        return codename;
    }

    public static boolean contains(String name) {
        for (BrowserOS osv : BrowserOS.values()) {
            if (osv.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<BrowserOS> VALUES = Collections.unmodifiableList( Arrays.asList( values() ) );
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE)).getName();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{name='").append(name).append('\'');
        sb.append(", codename='").append(codename).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String [] args) {
        BrowserOS os = BrowserOS.WINDOWS_7;
        System.out.println(os);
        System.out.println("platform=" + os.getName());
        System.out.println("codename=" + os.getCodename());
        System.out.println("toString=" + os.toString());
        System.out.println("contains(true)=" + BrowserOS.contains(BrowserOS.WINDOWS_XP.getName()));
        System.out.println("contains(false)=" + BrowserOS.contains("browser"));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(BrowserOS.getRandom());
    }

}

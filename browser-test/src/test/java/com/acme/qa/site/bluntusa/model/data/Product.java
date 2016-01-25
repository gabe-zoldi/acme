/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.model.data;

import java.util.*;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
public enum Product {

    BLUNT_XS_METRO_UMBRELLA (Type.UMBRELLA, "BLUNT XS_Metro Umbrella", "BLUNT XS_Metro Umbrella"),
    BLUNT_MINIPLUS_UMBRELLA (Type.UMBRELLA, "BLUNT Mini+ Umbrella", "BLUNT Mini+ Umbrella"),
    BLUNT_LITE_UMBRELLA     (Type.UMBRELLA, "BLUNT Lite Umbrella", "BLUNT Lite Umbrella"),
    FOOBAR                  (Type.UMBRELLA, "foobar", "foobar"),
    DEFAULT                 (Type.UMBRELLA, "BLUNT XS_Metro Umbrella", "BLUNT XS_Metro Umbrella");

    public class Type {
        public static final String UMBRELLA = "Umbrella Range";
    }

    private String type;
    private String title;
    private String alt;

    /**
     * A mapping between the title and its corresponding Product to facilitate lookup by title.
     */
    private static Map<String, Product> titleToProductMapping;

    private Product(String type, String title, String alt) {
        this.type = type;
        this.title = title;
        this.alt = alt;
    }

    public static Product getProduct(String title) {
        if (titleToProductMapping == null) {
            initMapping();
        }
        return titleToProductMapping.get(title);
    }

    private static void initMapping() {
        titleToProductMapping = new HashMap<String, Product>();
        for (Product p: values()) {
            titleToProductMapping.put(p.title, p);
        }
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getAlt() {
        return alt;
    }

    public static boolean contains(String title) {
        for (Product p : Product.values()) {
            if (p.title.equals(title)) {
                return true;
            }
        }
        return false;
    }

    /*
     * randomizer
     */
    private static final List<Product> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static Product getRandom()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("{type='").append(type).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", alt='").append(alt).append('\'');
        sb.append('}');
        return sb.toString();
    }


    public static void main(String[] args) {
        Product p = Product.BLUNT_XS_METRO_UMBRELLA;
        System.out.println(p);
        System.out.println("type=" + p.getType());
        System.out.println("title=" + p.getTitle());
        System.out.println("alt=" + p.getAlt());
        System.out.println("toString=" + p.toString());
        System.out.println("contains(true)=" + Product.contains( Product.BLUNT_LITE_UMBRELLA.getTitle() ));
        System.out.println("contains(false)=" + Product.contains("foobar"));

        System.out.println("\n((( randomizer )))");
        for (int i = 0; i < 3; i++)
            System.out.println(Product.getRandom());
    }

}

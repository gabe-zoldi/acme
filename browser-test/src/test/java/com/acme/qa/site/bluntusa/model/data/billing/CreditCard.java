/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.model.data.billing;

import com.acme.qa.common.type.Month;
import com.acme.qa.common.type.Year;
import com.acme.qa.common.utilities.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
@EqualsAndHashCode(callSuper=false)
@Data
public class CreditCard extends PaymentMethod {
    /*
     * Note: IntelliJ will complain about getter/setters if you don't have the lombok plugin
     * goto File > Settings > Plugins > Browse repositories...search for 'lombok', install it
     */
    private String name        = null;
    private String number      = null;
    private String expiryMonth = null;
    private String expiryYear  = null;
    private String cvc         = null;

    public static class TestCard {
        /*
         * stripe provided test card numbers (https://stripe.com/docs/testing)
         */
        public static class Stripe {
            // these cards should all work
            public static class POS {
                public static final String VISA_1     = "4242424242424242";
                public static final String VISA_2     = "4012888888881881";
                public static final String MC_1       = "5555555555554444";
                public static final String MC_2       = "5105105105105100";
                public static final String AMEX_1     = "378282246310005";
                public static final String AMEX_2     = "371449635398431";
                public static final String DISCOVER_1 = "6011111111111117";
                public static final String DISCOVER_2 = "6011000990139424";
                public static final String DINERS_1   = "30569309025904";
                public static final String DINERS_2   = "38520000023237";
                public static final String JCB_1      = "3530111333300000";
                public static final String JCB_2      = "3566002020360505";
                public static final String VISA_DEBIT = "4000056655665556";
                public static final String MC_DEBIT   = "5200828282828210";
            }
            // these cards will fail, some with special error codes
            public static class NEG {
                public static final String INCORRECT_NUMBER                         = "4242424242424241";
                public static final String CARD_DECLINED_ALWAYS                     = "4000000000000002";
                public static final String CARD_DECLINED_WITH_INCORRECT_CVC         = "4000000000000127";
                public static final String CARD_DECLINED_WITH_EXPIRED_CARD          = "4000000000000069";
                public static final String CARD_DECLINED_WITH_PROCESSING_ERROR_CODE = "4000000000000119";
            }
        }
        public static final String ALL_ZEROES = "0000000000000000";
        public static final String ALL_ONES   = "1111111111111111";
    }

    public static CreditCard createFake(String cardName, String cardNumber) {
        CreditCard cc = new CreditCard();
        cc.setName( cardName );
        cc.setNumber( cardNumber );
        cc.setExpiryMonth( getRandomExpiryMonth() );
        cc.setExpiryYear( getRandomExpiryYear() );
        cc.setCvc( getRandomCVC() );
        return cc;
    }


    public static String getExpiryMonthDefault() {
        return Month.DEFAULT.getText();
    }

    public static String getExpiryYearDefault() {
        return Year.DEFAULT.getText();
    }

    public static String getRandomExpiryMonth() {
        return Month.getRandom();
    }

    public static String getRandomExpiryYear() {
        // let's pick start year far enough into the future,
        // and not include the current year or the next
        return String.valueOf( Utils.getRandomWithinRange(2016, 2030) );
    }

    public static String getRandomCVC() {
        // security code must contain at least 3 digits but no more than 4 digits  http://en.wikipedia.org/wiki/Card_security_code
        return String.valueOf( Utils.getRandomWithinRange(1000, 9999) );
    }

}

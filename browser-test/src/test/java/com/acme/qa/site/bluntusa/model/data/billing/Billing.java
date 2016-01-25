/*
 * **********************************************************
 *  Copyright (c) 2014 Acme, Inc.  All rights reserved.
 * *********************************************************
 */
package com.acme.qa.site.bluntusa.model.data.billing;

import lombok.Data;

/**
 * User: gzoldi <br/> Date: 8/30/14 <br/> Time: 07:11 AM <br/>
 */
@Data
public class Billing {

    // supported payment methods can change in the future
    // to also accept checks, paypal, bitcoins, etc.
    private PaymentMethod paymentMethod = null;

    public Billing(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public static Billing createFakeCreditCard(String name) {
        return new Billing( CreditCard.createFake( name, CreditCard.TestCard.Stripe.POS.VISA_1 ) );
    }

}

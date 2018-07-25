package edu.pdx.cs410J.tmassey;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PhoneBillTest {

    @Test
    public void getCustomerReturnsACustomer() {
        PhoneBill bill = new PhoneBill("New Customer");
        assertThat(bill.getCustomer(), containsString("New Customer"));
    }

    @Test
    public void addPhoneCallAddsAPhoneCall() {
        PhoneBill bill = new PhoneBill("");
        PhoneCall call = new PhoneCall("", "", "01/11/2018 13:08", "01/11/2018 13:14");
        bill.addPhoneCall(call);
        assertThat(bill.getPhoneCalls().size(), is(1));
    }

    @Test
    public void phoneBillAddsACustomer() {
        PhoneBill bill = new PhoneBill("Tom");
        PhoneCall call = new PhoneCall("", "", "01/11/2018 13:08", "01/11/2018 13:14");
        bill.addPhoneCall(call);
        equals(bill.getPhoneCalls().contains(call));
    }

}
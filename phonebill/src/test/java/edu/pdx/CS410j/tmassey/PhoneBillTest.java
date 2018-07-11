package edu.pdx.CS410j.tmassey;

import org.junit.Test;

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
        PhoneCall call = new PhoneCall("", "", "", "");
        bill.addPhoneCall(call);
        assertThat(bill.getPhoneCalls().size(), is(1));
    }

    @Test
    public void phoneBillAddsACustomer() {
        PhoneBill bill = new PhoneBill("Tom");
        PhoneCall call = new PhoneCall("", "", "", "");
        bill.addPhoneCall(call);
        equals(bill.getPhoneCalls().contains(call));
    }

}

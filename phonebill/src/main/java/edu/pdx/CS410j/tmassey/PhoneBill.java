package edu.pdx.CS410j.tmassey;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill  extends AbstractPhoneBill<PhoneCall>{
    private final String customerName;
    private Collection<PhoneCall> calls = new ArrayList<>();

    public PhoneBill(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String getCustomer() {
        return this.customerName;
    }

    @Override
    public void addPhoneCall(PhoneCall phoneCall) {
        this.calls.add(phoneCall);
    }

    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return this.calls;
    }
}

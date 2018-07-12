package edu.pdx.CS410j.tmassey;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

/**
 * class to create a PhoneBill with a customer and a collection of phone calls
 *
 *
 */
public class PhoneBill  extends AbstractPhoneBill<PhoneCall>{
    private final String customerName;
    private Collection<PhoneCall> calls = new ArrayList<>();

    /**
     * Constructor to create a PhoneBill
     * @param customerName
     */
    public PhoneBill(String customerName) {
        this.customerName = customerName;
    }

    /**
     * getCustomer method returns the customer name for this phone bill object
     * @return String customerName
     */
    @Override
    public String getCustomer() {
        return this.customerName;
    }

    /**
     * addPhoneCall method adds a phone call to the phonebill class for Customer
     * @param phoneCall
     */
    @Override
    public void addPhoneCall(PhoneCall phoneCall) {
        this.calls.add(phoneCall);
    }

    /**
     * getPhoneCalls returns a collection of phoneCalls that would be found in a phone bill
     * @return
     */
    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return this.calls;
    }
}

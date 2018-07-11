package edu.pdx.CS410j.tmassey;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.Collection;

/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  public static void main(String[] args) {
    String customer = new String();
    String caller = new String();
    String callee = new String();
    String startTime = new String();
    String endTime = new String();
//
//    PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);  // Refer to one of Dave's classes so that we can be sure it is on the classpath
//
//    PhoneBill bill = new PhoneBill("New Caller");
//    bill.addPhoneCall(call);

//    Collection<PhoneCall> phoneCalls = bill.getPhoneCalls();
//    for(PhoneCall c: phoneCalls) {
//      System.out.println(c);
//    }

    var dateTimeRegEx = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d [0-2]\\d:[0-6]\\d:[0-6]\\d)?";
    var phoneRegEx = "^\\d{3}-\\d{3}-\\d{4}$";

    Boolean errorOnInput = false;

    if(!args[0].toLowerCase().equals("customer")) {
      System.err.println("Missing command line argument: customer");
      errorOnInput = true;
    }

    if(!args[1].toLowerCase().matches("^[a-zA-Z0-9]*$")) {
      System.err.println("customerValue must be alphanumeric (0-9 and A-Z):");
      errorOnInput = true;
    } else {
      customer = args[1];
    }

    if(!args[2].toLowerCase().equals("callernumber")) {
      System.err.println("Missing command line argument: callerNumber");
      errorOnInput = true;
    }

    if(!args[3].toLowerCase().matches(phoneRegEx)) {
      System.err.println("Missing command line arguments: callerNumberValue");
      errorOnInput = true;
    } else {
      caller = args[3];
    }

    if(!args[4].toLowerCase().equals("calleenumber")) {
      System.err.println("Missing command line argument: calleeNumber");
      errorOnInput = true;
    }

    if(!args[5].toLowerCase().contains("")) {
      System.err.println("Missing command line arguments: calleeNumberValue");
      errorOnInput = true;
    } else {
      callee = args[5];
    }

    if(!args[6].toLowerCase().equals("starttime")) {
      System.err.println("Missing command line argument: startTime");
      errorOnInput = true;
    }

    if(!args[7].toLowerCase().matches(dateTimeRegEx)) {
      System.err.println("Missing command line argument: startTimeValue");
      errorOnInput = true;
    } else {
      startTime = args[7];
    }

    if(!args[8].toLowerCase().equals("endtime")) {
      System.err.println("Missing command line argument: endTime");
      errorOnInput = true;
    }

    if(!args[9].toLowerCase().matches(dateTimeRegEx)) {
      System.err.println("Missing command line argument: endTimeValue");
      errorOnInput = true;
    } else {
      endTime = args[9];
    }

    if(errorOnInput) {
      System.exit(0);
    } else {
      PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);
      PhoneBill bill = new PhoneBill(customer);
      bill.addPhoneCall(call);
    }

    for (String arg : args) {
      System.out.println(arg);
    }
    System.exit(1);
  }

}
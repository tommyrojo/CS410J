package edu.pdx.cs410J.tmassey;

import java.util.Collection;

/**
 * The main class for the cs410J Phone Bill Project
 */
public class Project1 {

  public static void main(String[] args) {
    String customer = new String();
    String caller = new String();
    String callee = new String();
    String startTime = new String();
    String endTime = new String();
    Boolean readMe = false;
    Boolean print = false;

    var dateRegEx = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)?";
    var timeRegEx = "([0-2]\\d:[0-6]\\d:[0-6]\\d)";
    var phoneRegEx = "^\\d{3}-\\d{3}-\\d{4}$";

    Boolean errorOnInput = false;

    if (args.length > 0){
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-README")) {
          System.out.println("Tom Massey");
          System.out.println("cs410J");
          System.out.println("This is a simple PhoneBill project that contains a collection of Phone Calls");
          System.out.println("The point was to extend a couple of Abstact Classes that we did not write");
          System.out.println("and get that functionality working from the command line");
          System.out.println("including flags and options provided by the user");
          System.out.println("as well as error checking upon input");
          System.exit(1);
        }
      }

      if (args.length >= 7) {

        if (!args[0].toLowerCase().matches("^[a-zA-Z0-9 ]*$")) {
          System.err.println("customerValue must be alphanumeric (0-9 and A-Z):");
          errorOnInput = true;
        } else {
          customer = args[0];
        }


        if (!args[1].toLowerCase().matches(phoneRegEx)) {
          System.err.println("callerNumberValue must be in format: ###-###-####");
          errorOnInput = true;
        } else {
          caller = args[1];
        }


        if (!args[2].toLowerCase().matches(phoneRegEx)) {
          System.err.println("calleeNumberValue must be in format: ###-###-####");
          errorOnInput = true;
        } else {
          callee = args[2];
        }

        if (!args[3].toLowerCase().matches(dateRegEx)) {
          System.err.println("startDateValue must be in format: mm/dd/yyyy");
          errorOnInput = true;
        } else {
          startTime = args[3];
        }

        if (!args[4].toLowerCase().matches(timeRegEx)) {
          System.err.println("startTimeValue must be in format: hh:mm");
          errorOnInput = true;
        } else {
          startTime = args[4];
        }

        if (!args[5].toLowerCase().matches(dateRegEx)) {
          System.err.println("endDateValue must be in format: mm/dd/yyyy");
          errorOnInput = true;
        } else {
          endTime = args[5];
        }

        if (!args[6].toLowerCase().matches(timeRegEx)) {
          System.err.println("endTimeValue must be in format: hh:mm");
          errorOnInput = true;
        } else {
          endTime = args[6];
        }

        if (errorOnInput) {
          System.exit(0);
        } else {
          PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);
          PhoneBill bill = new PhoneBill(customer);
          bill.addPhoneCall(call);

          if (args.length > 10) {
            for (int i = 10; i < args.length; i++) {
              if (args[i].equals("-README")) {
                System.out.println("Tom Massey");
                System.out.println("cs410J");
                System.out.println("This is a simple PhoneBill project that contains a collection of Phone Calls");
                System.out.println("The point was to extend a couple of Abstact Classes that we did not write");
                System.out.println("and get that functionality working from the command line");
                System.out.println("including flags and options provided by the user");
                System.out.println("as well as error checking upon input");
              }

              if (args[i].equals("-print")) {
                Collection<PhoneCall> phoneCalls = bill.getPhoneCalls();
                for (PhoneCall c : phoneCalls) {
                  System.out.println(c);
                }
              }
            }
          }

          System.exit(1);
        }
    }


    } else {
        System.err.println("Please check the required input fields which include:");
        System.err.println("customer\t(alphanumeric)");
        System.err.println("callerNumber\t(###-###-####)");
        System.err.println("calleeNumber\t(###-###-####)");
        System.err.println("startTime\t(MM/DD/YYYY HH:MM:SS)");
        System.err.println("endTime\t(MM/DD/YYYY HH:MM:SS)");
        System.err.println("optional fields include:");
        System.err.println("-print");
        System.err.println("-README");
        System.exit(0);
    }

  }
}
package edu.pdx.CS410J.tmassey;

import java.io.*;
import java.util.Collection;

public class Project2 {

    public static void main(String[] args) {
        String customer = new String();
        String caller = new String();
        String callee = new String();
        String startTime = new String();
        String endTime = new String();
        Boolean readMe = false;
        Boolean print = false;

        var dateTimeRegEx = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d [0-2]\\d:[0-6]\\d:[0-6]\\d)?";
        var phoneRegEx = "^\\d{3}-\\d{3}-\\d{4}$";

        Boolean errorOnInput = false;

        if (args.length >= 10) {

            if (!args[0].toLowerCase().equals("customer")) {
                System.err.println("Missing command line argument: customer");
                errorOnInput = true;
            }

            if (!args[1].toLowerCase().matches("^[a-zA-Z0-9 ]*$")) {
                System.err.println("customerValue must be alphanumeric (0-9 and A-Z):");
                errorOnInput = true;
            } else {
                customer = args[1];
            }

            if (!args[2].toLowerCase().equals("callernumber")) {
                System.err.println("Missing command line argument: callerNumber");
                errorOnInput = true;
            }

            if (!args[3].toLowerCase().matches(phoneRegEx)) {
                System.err.println("callerNumberValue must be in format: ###-###-####");
                errorOnInput = true;
            } else {
                caller = args[3];
            }

            if (!args[4].toLowerCase().equals("calleenumber")) {
                System.err.println("Missing command line argument: calleeNumber");
                errorOnInput = true;
            }

            if (!args[5].toLowerCase().matches(phoneRegEx)) {
                System.err.println("calleeNumberValue must be in format: ###-###-####");
                errorOnInput = true;
            } else {
                callee = args[5];
            }

            if (!args[6].toLowerCase().equals("starttime")) {
                System.err.println("Missing command line argument: startTime");
                errorOnInput = true;
            }

            if (!args[7].toLowerCase().matches(dateTimeRegEx)) {
                System.err.println("startTimeValue must be in format: mm/dd/yyyy hh:mm");
                errorOnInput = true;
            } else {
                startTime = args[7];
            }

            if (!args[8].toLowerCase().equals("endtime")) {
                System.err.println("Missing command line argument: endTime");
                errorOnInput = true;
            }

            if (!args[9].toLowerCase().matches(dateTimeRegEx)) {
                System.err.println("endTimeValue must be in format: mm/dd/yyyy hh:mm");
                errorOnInput = true;
            } else {
                endTime = args[9];
            }
        } else {
            System.err.println("Please verify your input parameters...");
            System.exit(0);
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
                        System.out.println("CS410J");
                        System.out.println("This is a simple PhoneBill project that contains a collection of Phone Calls");
                        System.out.println("The point was to extend a couple of Abstact Classes that we did not write");
                        System.out.println("and get that functionality working from the command line");
                        System.out.println("including flags and options provided by the user");
                        System.out.println("as well as error checking upon input");
                    }

                    if (args[i].equals("-print")) {
                        Collection<PhoneCall> phoneCalls = bill.getPhoneCalls();
                        for(PhoneCall c: phoneCalls) {
                            System.out.println(c);
                        }
                    }

                    if (args[i].equals("-textFile")) {
                        String fileName = args[i + 1];
                        String text = "Hello world Linux";
                        try {

                            String desktop = System.getProperty ("user.home") + "/Desktop/";
                            File file = new File (desktop + fileName + ".csv");

                            BufferedWriter output = new BufferedWriter(new FileWriter(file));
                            output.write(text);
                            output.close();
                        } catch ( IOException e ) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        System.exit(1);
    }
}

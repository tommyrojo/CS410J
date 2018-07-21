package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The main class for the cs410J Phone Bill Project
 */
public class Project2 {

    public static void main(String[] args) throws IOException {
        String customer = new String();
        String caller = new String();
        String callee = new String();
        String startDate = new String();
        String startTime = new String();
        String endDate = new String();
        String endTime = new String();
        var startPos = 0;
        var fileName = new String();
        var fileNameFlag = false;
        Boolean readMe = false;
        Boolean print = false;
        var errorInput = new ArrayList<String>();
        AbstractPhoneBill bill = new PhoneBill("empty");

        /**
         * regular expressions to assist with input validation
         */
        var dateRegEx = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)?";
        var timeRegEx = "([0-2]\\d:[0-6]\\d)";
        var phoneRegEx = "^\\d{3}-\\d{3}-\\d{4}$";

        Boolean errorOnInput = false;

        List<String> argsOrder = Arrays.asList("customer", "caller", "callee", "startDate", "startTime", "endDate", "endTime", "textFile");
        LinkedHashMap<String, String> argPos = new LinkedHashMap<>();

        /**
         * here we loop through each input scanning for flags and required fields
         */
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-README")) {
                System.out.println("Tom Massey");
                System.out.println("cs410J");
                System.out.println("This is a simple PhoneBill project that contains a collection of Phone Calls");
                System.out.println("The point was to extend a couple of Abstact Classes that we did not write");
                System.out.println("and get that functionality working from the command line");
                System.out.println("including flags and options provided by the user");
                System.out.println("as well as error checking upon input");
                startPos++;
                System.exit(1);
            }

            /**
             * we want to account for -print being anywhere in the input so we
             * increment a startPos so the rest of the args can be scanned correctly
             */
            if (args[i].equals("-print")) {
                print = true;
                startPos++;
            }

            if (args[i].equals("-textFile")) {
                fileName = args[i + 1];
                if (fileName.length() > 0)
                    fileNameFlag = true;

                startPos++;
            }

            /**
             * omit -README and -print to scan the rest of the inputs and track
             * erroneous inputs
             */
            if (!args[i].equals("-README") && !args[i].equals("-print") && !args[i].equals("-textFile")) {
                if (argPos.size() < 8 && !(args[i].charAt(0) == '-')) {
                    argPos.put(argsOrder.get(i - startPos), args[i]);
                } else {
                    errorInput.add(args[i]);
                    startPos++;
                }
            }
        }

        /**
         * if we encounter bad input, display to user
         */
        if (errorInput.size() > 0) {
            System.out.println("Invalid inputs include :" + errorInput);
            errorOutput();
        }

        /**
         * if we are missing input, through in a bad value
         * so it fails the regex and outputs the correct field to correct
         */
        for (int i = argPos.size(); i < 8; i++) {
            argPos.put(argsOrder.get(i), "@@@_BREAK_ME_@@@");
        }


        /**
         * field input validations
         */
        if (!argPos.get("customer").toLowerCase().matches("^[a-zA-Z0-9 ]*$")) {
            System.err.println("customerValue must be alphanumeric (0-9 and A-Z):");
            errorOnInput = true;
        } else {
            customer = argPos.get("customer");
        }


        if (!argPos.get("caller").toLowerCase().matches(phoneRegEx)) {
            System.err.println("callerNumberValue must be in format: ###-###-####");
        } else {
            caller = argPos.get("caller");
        }


        if (!argPos.get("callee").toLowerCase().matches(phoneRegEx)) {
            System.err.println("calleeNumberValue must be in format: ###-###-####");
        } else {
            callee = argPos.get("callee");
        }

        if (!argPos.get("startDate").toLowerCase().matches(dateRegEx)) {
            System.err.println("startDateValue must be in format: mm/dd/yyyy");
        } else {
            startDate = argPos.get("startDate");
        }

        if (!argPos.get("startTime").toLowerCase().matches(timeRegEx)) {
            System.err.println("startTimeValue must be in format: hh:mm");
        } else {
            startTime = argPos.get("startTime");
        }

        if (!argPos.get("endDate").toLowerCase().matches(dateRegEx)) {
            System.err.println("endDateValue must be in format: mm/dd/yyyy");
        } else {
            endDate = argPos.get("endDate");
        }

        if (!argPos.get("endTime").toLowerCase().matches(timeRegEx)) {
            System.err.println("endTimeValue must be in format: hh:mm");
        } else {
            endTime = argPos.get("endTime");
        }

        /**
         * if we have errors, output error text,
         * else print output of phone call if print is specified
         * else do nothing
         */
        if (errorOnInput) {
            errorOutput();
            System.exit(0);
        } else {
            if (fileNameFlag) {
                String text = "Hello world Linux";

                TextParser parser = new TextParser(fileName);
                try {
                    bill = parser.parse();
                } catch (ParserException e) {
                    e.printStackTrace();
                }

                if (bill == null) {
                    bill = new PhoneBill(customer);
                    PhoneCall call = new PhoneCall(caller, callee, startDate + " " + startTime, endDate + " " + endTime);
                    bill.addPhoneCall(call);
                } else if (customer.equals(bill.getCustomer())) {
                    PhoneCall call = new PhoneCall(caller, callee, startDate + " " + startTime, endDate + " " + endTime);
                    bill.addPhoneCall(call);
                } else {
                    System.err.println(customer + " does not exist in fileName " + fileName);
                    System.exit(0);
                }

                TextDumper phoneBill = new TextDumper(fileName);
                phoneBill.dump(bill);
            }

            if (print) {
                Collection<PhoneCall> phoneCalls = bill.getPhoneCalls();
                for (PhoneCall c : phoneCalls) {
                    System.out.println(c);
                }
            }

            System.exit(1);
        }
    }

    /**
     * output function for errors that are encountered
     */
    private static void errorOutput() {
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
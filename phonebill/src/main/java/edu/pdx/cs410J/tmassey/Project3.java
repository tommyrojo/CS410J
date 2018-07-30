package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;

import java.io.IOException;
import java.util.*;

/**
 * The main class for the cs410J Phone Bill Project
 */
public class Project3 {

    private static boolean prettyNameFlag;
    private static boolean prettyStdOut;

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
        PhoneCall call = new PhoneCall("", "", new Date(), new Date());
        var amPmPos = new ArrayList<String>();
        var skip = false;
        var prettyName = new String();


        /**
         * regular expressions to assist with input validation
         */
        var dateTimeRegEx = "^((0|1)?\\d{1})/((0|1|2)?\\d{1})/((19|20)?\\d{2}) (((0?[1-9])|(1[0-2])):([0-5])\\d\\s(A|P|a|p)(M|m))";
        var phoneRegEx = "^\\d{3}-\\d{3}-\\d{4}$";

        Boolean errorOnInput = false;

        List<String> argsOrder = Arrays.asList("customer", "caller", "callee", "startDate", "startTime", "endDate", "endTime");
        LinkedHashMap<String, String> argPos = new LinkedHashMap<>();

        /**
         * here we loop through each input scanning for flags and required fields
         */
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-README")) {
                System.out.println("Tom Massey");
                System.out.println("cs410J");
                System.out.println("This is still a simple PhoneBill project that contains a collection of Phone Calls");
                System.out.println("The point is to build on previous work finished in Project2 and");
                System.out.println("verify date input as well as handle the 24 to 12 hour time change and");
                System.out.println("we also added some pretty printing");
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

            if (args[i].toUpperCase().equals("AM") || args[i].toUpperCase().equals("PM")) {
                amPmPos.add(args[i]);
                skip = true;
                startPos++;
            }

            if (args[i].equals("-textFile")) {
                fileName = args[i + 1];
                if (fileName.length() > 0)
                    fileNameFlag = true;

                startPos += 2;
                i += 2;

                if(args[i].equals("-print")) {
                    print = true;
                    startPos++;
                }

                if (args[i].equals("-pretty")) {
                    prettyNameFlag = true;
                    prettyName = args[i + 1];

                    if (prettyName.equals("-") && prettyName.length() == 1) {
                        prettyStdOut = true;
                    }

                    startPos += 2;
                    i++;
                    skip = true;
                }
            }

            if (args[i].equals("-pretty")) {
                prettyNameFlag = true;
                prettyName = args[i + 1];

                if (prettyName.equals("-") && prettyName.length() == 1) {
                    prettyStdOut = true;
                }

                startPos += 2;
                i++;
                skip = true;
            }

            /**
             * omit -README and -print to scan the rest of the inputs and track
             * erroneous inputs
             */
            if (
                !(i > args.length) &&
                !args[i].equals("-README") &&
                !args[i].equals("-print") &&
                !args[i].equals("-textFile") &&
                !args[i].equals("-pretty") &&
                !skip)
            {
                if (argPos.size() < 7 && !(args[i].charAt(0) == '-')) {
                    argPos.put(argsOrder.get(i - startPos), args[i]);
                } else {
                    errorInput.add(args[i]);
                    startPos++;
                }
            } else if (amPmPos.size() > 0){
                skip = false;
                var date = argPos.get(argsOrder.get(i - startPos)) + " " + amPmPos.get(0);
                amPmPos.remove(0);
                var newDate = argPos.get(argsOrder.get(i - startPos - 1));
                argPos.put(argsOrder.get(i - startPos - 1), newDate + " " + date);
            } else {
                skip = false;
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
        for (int i = argPos.size(); i < 7; i++) {
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
            errorOnInput = true;
        } else {
            caller = argPos.get("caller");
        }


        if (!argPos.get("callee").toLowerCase().matches(phoneRegEx)) {
            System.err.println("calleeNumberValue must be in format: ###-###-####");
            errorOnInput = true;
        } else {
            callee = argPos.get("callee");
        }

        if (!argPos.get("startDate").toLowerCase().matches(dateTimeRegEx)) {
            System.err.println("startDateValue must be in format: mm/dd/yyyy hh:mm and am/pm");
            errorOnInput = true;
        } else {
            startDate = argPos.get("startDate");
        }

        if (!argPos.get("endDate").toLowerCase().matches(dateTimeRegEx)) {
            System.err.println("endDateValue must be in format: mm/dd/yyyy hh:mm and am/pm");
            errorOnInput = true;
        } else {
            endDate = argPos.get("endDate");
        }

        /**
         * if we have errors, output error text,
         * else print output of phone call if print is specified
         * else do nothing
         */

        if (errorOnInput) {
            System.exit(0);
        } else {

            /**
             * quick check to verify that the call orders are entered in the correct order
             */
            if (new Date(endDate).getTime() < new Date(startDate).getTime()) {
                System.err.println("endDate must be more recent than beginDate");
                System.exit(0);
            }

            /**
             * if we have a fileName flag, first check if it exists, if it does read in the data
             * if it doesn't return null so we can create a new file in TextDumper
             */
            if (fileNameFlag || prettyNameFlag) {

                /**
                 * checking to see if file exists
                 */
                if (fileNameFlag) {
                    TextParser parser = new TextParser(fileName);
                    try {
                        bill = parser.parse();
                    } catch (ParserException e) {
                        e.printStackTrace();
                    }

                }

                /**
                 * if we come back with an empty bill, we create a new bill based off the command line
                 * if we come back with a bill, we check that the name in the bill matches the value passed in
                 * if it does not match, we exit with an error
                 */
                if (bill == null || bill.getCustomer().equals("empty")) {
                    bill = new PhoneBill(customer);
                    call = new PhoneCall(caller, callee, new Date(startDate), new Date(endDate));
                    bill.addPhoneCall(call);
                } else if (customer.equals(bill.getCustomer())) {
                    call = new PhoneCall(caller, callee, new Date(startDate), new Date(endDate));
                    bill.addPhoneCall(call);
                } else {
                    System.err.println(customer + " does not exist in fileName " + fileName);
                    System.exit(0);
                }

                /**
                 * move to text dumper with new file or existing file with new entries
                 */
                if (prettyStdOut) {
                    System.out.println(call.toString() +
                            " Call Lasted for: " +
                            new PrettyPrinter("").PhoneCallLength(call.getEndTime(), call.getStartTime()));
                } else if (prettyNameFlag) {
                    PrettyPrinter pretty = new PrettyPrinter(prettyName);
                    pretty.dump(bill);
                }

                if (fileNameFlag) {
                    TextDumper phoneBill = new TextDumper(fileName);
                    phoneBill.dump(bill);
                }
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
        System.err.println();
        System.err.println("Please check the required input fields which include:");
        System.err.println("customer\t(alphanumeric)");
        System.err.println("callerNumber\t(###-###-####)");
        System.err.println("calleeNumber\t(###-###-####)");
        System.err.println("startTime\t(MM/DD/YYYY HH:MM:SS)");
        System.err.println("endTime\t(MM/DD/YYYY HH:MM:SS)");
        System.err.println("optional fields include:");
        System.err.println("-print");
        System.err.println("-README");
        System.err.println();
        System.exit(0);
    }
}
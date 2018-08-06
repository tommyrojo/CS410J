package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";
    public static final String dateTimeRegEx = "^((0|1)?\\d{1})/((0|1|2)?\\d{1})/((19|20)?\\d{2}) (((0?[1-9])|(1[0-2])):([0-5])\\d\\s(A|P|a|p)(M|m))";
    public static final String phoneRegEx = "^\\d{3}-\\d{3}-\\d{4}$";
    private static boolean searchFlag = false;

    public static void main(String... args) throws IOException {
        String hostName = null;
        String portString = null;
        String customer = null;
        String callerNumber = null;
        String calleeNumber = null;


        String caller = new String();
        String callee = new String();
        String startDate = new String();
        String startTime = new String();
        String endDate = new String();
        String endTime = new String();
        var startPos = 0;
        Boolean readMe = false;
        Boolean print = false;
        var errorInput = new ArrayList<String>();

        PhoneCall call = new PhoneCall("", "", new Date(), new Date());
        var amPmPos = new ArrayList<String>();
        var skip = false;
        var prettyName = new String();

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
                System.out.println("This is Rest Project that is using a client/server setup");
                System.out.println("The point is to build on previous work finished in Project3 and");
                System.out.println("extend the project to use a web browser and a server to test REST endpints");
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
                skip = true;
            }

            if (args[i].toUpperCase().equals("AM") || args[i].toUpperCase().equals("PM")) {
                amPmPos.add(args[i]);
                skip = true;
                startPos++;
            }

            if (args[i].equals("-host")) {
                hostName = args[i + 1];
                startPos += 2;
                i++;
                skip = true;
            }

            if (args[i].equals("-port")) {
                portString = args[i + 1];
                startPos +=2;
                i++;
                skip = true;
            }

            if (args[i].equals("-search")) {
                searchFlag = true;
                startPos++;
                skip = true;
                argsOrder = Arrays.asList("customer", "startDate", "startTime", "endDate", "endTime");
            }

            /**
             * omit -README and -print to scan the rest of the inputs and track
             * erroneous inputs
             */
            if (!(i > args.length) && !skip)
            {
                if (argPos.size() < argsOrder.size() && !(args[i].charAt(0) == '-')) {
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
        if (hostName == null) {
            usage( MISSING_ARGS );

        } else if ( portString == null) {
            usage( "Missing port" );
        }

        int port;
        try {
            port = Integer.parseInt( portString );

        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        /**
         * if we encounter bad input, display to user
         */
        if (errorInput.size() > 0) {
            usage(errorInput.toString());
        }

        if (argPos.size() > 1) {

            /**
             * if we are missing input, through in a bad value
             * so it fails the regex and outputs the correct field to correct
             */
            for (int i = argPos.size(); i < argsOrder.size(); i++) {
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


            if (!searchFlag && !argPos.get("caller").toLowerCase().matches(phoneRegEx)) {
                System.err.println("callerNumberValue must be in format: ###-###-####");
                errorOnInput = true;
            } else {
                caller = argPos.get("caller");
            }


            if (!searchFlag && !argPos.get("callee").toLowerCase().matches(phoneRegEx)) {
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
        } else {
            startDate = new Date(0).toString();
            endDate = new Date("01/01/2020 12:34 pm").toString();
            customer = argPos.get("customer");
        }


        PhoneBillRestClient client = new PhoneBillRestClient(hostName, port);
        String customerName = customer;

        if (searchFlag || argPos.size() == 1) {
            client.searchPhoneBill(customerName, startDate, endDate);
            if (argPos.size() == 1) {
                PhoneBill bill = new PhoneBill(customer);

                for (PhoneCall c : bill.getPhoneCalls()) {
                    System.out.println(c);
                }

            }
        } else {
            Date DateStart = new Date(startDate);
            Date DateEnd = new Date(endDate);

            PhoneCall phoneCall = new PhoneCall(caller, callee, DateStart, DateEnd);
            client.addPhoneCall(customer, phoneCall);

            String message;

            // Print all word/definition pairs

            message = client.getPrettyPhoneBill(customerName);

            System.out.println(message);
        }
        System.exit(0);
    }

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getCode(), response.getContent()));
        }
    }

    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);

        System.exit(1);
    }

    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 host port [word] [definition]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  word         Word in dictionary");
        err.println("  definition   Definition of word");
        err.println();
        err.println("This simple program posts words and their definitions");
        err.println("to the server.");
        err.println("If no definition is specified, then the word's definition");
        err.println("is printed.");
        err.println("If no word is specified, all dictionary entries are printed");
        err.println();

        System.exit(1);
    }
}
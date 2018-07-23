package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.*;

public class TextParser implements PhoneBillParser<AbstractPhoneBill> {
    private String fileName;

    public TextParser(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public AbstractPhoneBill parse() throws ParserException {
        var dateRegEx = "^((0|1)\\d{1})/((0|1|2)\\d{1})/((19|20)\\d{2})";
        var timeRegEx = "([0-2]\\d:[0-6]\\d)";
        var phoneRegEx = "^\\d{3}-\\d{3}-\\d{4}$";

        String cwd = System.getProperty("user.dir");

        String dirName = fileName.split("/")[0];
        String fileNameOnly = fileName.split("/")[1];

        File file = new File(cwd + "/" + dirName + "/" + fileNameOnly);

        /**
         * reading in existing file if it exists
         * if it doesn't exist, catch exception and continue
         */
        try {
            InputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            /**
             * three arrays to catch parts of the file for the phone call
             */
            var callParts = new String[2];
            var phoneParts = new String[2];
            var dateParts = new String[2];

            /**
             * grabbing first line of the file to read in the customer name
             */
            var customer = br.readLine().split("'")[0];
            PhoneBill bill = new PhoneBill(customer);

            /**
             * read the reset of the fill and add an entry for each call into the bill
             */
            for (String line = br.readLine(); line != null; line = br.readLine()) {

                callParts = line.split("from");
                phoneParts = callParts[1].split("to");
                dateParts = callParts[2].split("to");

                var caller = phoneParts[0].trim().matches(phoneRegEx) ? phoneParts[0].trim() : null;
                var callee = phoneParts[1].trim().matches(phoneRegEx) ? phoneParts[1].trim() : null;

                var startTime = dateParts[0].trim().split(" ")[0].matches(dateRegEx) &&
                        dateParts[0].trim().split(" ")[1].matches(timeRegEx) ? dateParts[0].trim() : null;

                var endTime = dateParts[1].trim().split(" ")[0].matches(dateRegEx) &&
                        dateParts[1].trim().split(" ")[1].matches(timeRegEx) ? dateParts[1].trim() : null;

                if (caller == null) {
                    System.err.println("there is an issue with the caller input in the " + fileNameOnly + " file");
                    System.exit(0);
                } else if (callee == null) {
                    System.err.println("there is an issue with the callee input in the " + fileNameOnly + " file");
                    System.exit(0);
                } else if (startTime == null) {
                    System.err.println("there is an issue with the startTime input in the " + fileNameOnly + " file");
                    System.exit(0);
                } else if (endTime == null) {
                    System.err.println("there is an issue with the endTime input in the " + fileNameOnly + " file");
                    System.exit(0);
                }

                PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);

                bill.addPhoneCall(call);


            }
            /**
             * close buffer reader and return the newly built bill
             */
            br.close();
            return bill;

        /**
         * if we don't have a file that exists, pass back null so we can create
         * the file in text dumper
         */
        } catch (Exception e) {
            if (e.toString().equals("java.lang.ArrayIndexOutOfBoundsException: 1")){
                System.err.println("File is corrupt or not found");
                System.exit(0);
            } else {
                return null;
            }
        }
        return null;
    }
}

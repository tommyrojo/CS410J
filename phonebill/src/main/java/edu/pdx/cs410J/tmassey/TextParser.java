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
        String desktop = System.getProperty("user.home") + "/Desktop/";
        File file = new File(desktop + fileName + ".txt");

        try {
            InputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            var callParts = new String[2];
            var phoneParts = new String[2];
            var dateParts = new String[2];

            var customer = br.readLine().split("'")[0];
            PhoneBill bill = new PhoneBill(customer);

            for (String line = br.readLine(); line != null; line = br.readLine()) {

                callParts = line.split("from");
                phoneParts = callParts[1].split("to");
                dateParts = callParts[2].split("to");

                var caller = phoneParts[0].trim();
                var callee = phoneParts[1].trim();

                var startTime = dateParts[0].trim();
                var endTime = dateParts[1].trim();

                PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);

                bill.addPhoneCall(call);
            }

            br.close();
            return bill;

        } catch (Exception e) {
            return null;
        }
    }
}

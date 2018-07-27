package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class PrettyPrinter implements PhoneBillDumper<AbstractPhoneBill> {
    private String fileName;

    public PrettyPrinter(String fileName) {
        this.fileName = fileName;
    }

    public String PhoneCallLength(Date endTime, Date startTime) {

        long milliseconds = endTime.getTime() - startTime.getTime();
        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;
        String time = " " + days + " days " + hours % 24 + " hours " + minutes % 60 + " minutes " + seconds % 60 + " seconds ";

        return time;
    }

    @Override
    public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {
        /**
         * grab existing phone calls off of the passed in phone bill
         */
        ArrayList<PhoneCall> calls = (ArrayList<PhoneCall>) abstractPhoneBill.getPhoneCalls();

        /**
         * try to write to file name
         */
        try {
            String cwd = System.getProperty("user.dir");

            String dirName = fileName.split("/")[0];
            String fileNameOnly = fileName.split("/")[1];

            File dir = new File(cwd + "/" + dirName);
            dir.mkdirs();
            File file = new File(dir, fileNameOnly);

            BufferedWriter output = new BufferedWriter(new FileWriter(file));
            output.write(abstractPhoneBill.toString());

            Collections.sort(calls);

            for (PhoneCall c : calls) {
                output.write("\n\t" + c.toString() + PhoneCallLength(c.getEndTime(), c.getStartTime()));
            }

            /**
             * make sure to close our bufferedWriter
             */
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

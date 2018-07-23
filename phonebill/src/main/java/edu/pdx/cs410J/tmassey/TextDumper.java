package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import static java.lang.System.exit;

public class TextDumper implements PhoneBillDumper<AbstractPhoneBill> {
    private String fileName;

    public TextDumper(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void dump(AbstractPhoneBill abstractPhoneBill) throws IOException {

        /**
         * grab existing phone calls off of the passed in phone bill
         */
        Collection<PhoneCall> calls = abstractPhoneBill.getPhoneCalls();

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

            for (PhoneCall c : calls) {
                output.write("\n\t" + c.toString());
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

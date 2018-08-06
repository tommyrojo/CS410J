package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

/**
 * Tests the {@link Project4} class by invoking its main method with various arguments
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Project4IT extends InvokeMainTestCase {
    private static final String HOSTNAME = "localhost";
    private static final String PORT = System.getProperty("http.port", "8080");
    private static final String hostFlag = "-host";
    private static final String portFlag = "-port";
    private static final String searchFlag = "-search";

    @Test
    public void test0RemoveAllMappings() throws IOException {
      PhoneBillRestClient client = new PhoneBillRestClient(HOSTNAME, Integer.parseInt(PORT));
      client.removeAllPhoneBills();
    }

//    @Test
//    public void test1NoCommandLineArguments() {
//        MainMethodResult result = invokeMain( Project4.class );
//        assertThat(result.getExitCode(), equalTo(1));
//        assertThat(result.getTextWrittenToStandardError(), containsString(Project4.MISSING_ARGS));
//    }
//
//    @Test
//    public void test2EmptyServer() {
//        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT );
//        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
//        String out = result.getTextWrittenToStandardOut();
//        assertThat(out, out, containsString("Customer"));
//    }
//
//    @Test
//    public void test3NoDefinitions() {
//        String customer = "Customer";
//        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, customer );
//        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(0));
//        String out = result.getTextWrittenToStandardOut();
//        assertThat(out, out, containsString(customer));
//    }

    @Test
    public void test4AddDefinition() {
        String customer = "Dave";
        String callerNumber = "123-456-7890";
        String calleeNumber = "234-567-8901";
        String startDate = "01/01/2018";
        String startTime = "12:34";
        String startAmPm = "pm";
        String endDate = "01/01/2018";
        String endTime = "2:35";
        String endAmPm = "pm";

        MainMethodResult result = invokeMain( Project4.class, HOSTNAME, PORT, customer, callerNumber, calleeNumber, startDate, startTime, startAmPm, endDate, endTime, endAmPm );
        assertThat(result.getTextWrittenToStandardError(), result.getExitCode(), equalTo(1));
        String out = result.getTextWrittenToStandardOut();

        startDate = "01/02/2018";
        startTime = "2:34";
        startAmPm = "pm";
        endDate = "01/02/2018";
        endTime = "2:35";
        endAmPm = "pm";

        invokeMain( Project4.class, hostFlag, HOSTNAME, portFlag, PORT, customer, callerNumber, calleeNumber, startDate, startTime, startAmPm, endDate, endTime, endAmPm );

        startDate = "01/06/2018";
        startTime = "07:44";
        startAmPm = "am";
        endDate = "01/06/2018";
        endTime = "8:35";
        endAmPm = "am";

        invokeMain( Project4.class, hostFlag, HOSTNAME, portFlag, PORT, customer, callerNumber, calleeNumber, startDate, startTime, startAmPm, endDate, endTime, endAmPm );

        startDate = "01/29/2018";
        startTime = "11:14";
        startAmPm = "am";
        endDate = "01/29/2018";
        endTime = "1:35";
        endAmPm = "pm";


        invokeMain( Project4.class, hostFlag, HOSTNAME, portFlag, PORT, customer, callerNumber, calleeNumber, startDate, startTime, startAmPm, endDate, endTime, endAmPm );

        startDate = "02/03/2018";
        startTime = "10:01";
        startAmPm = "am";
        endDate = "02/03/2018";
        endTime = "12:05";
        endAmPm = "pm";

        invokeMain( Project4.class, hostFlag, HOSTNAME, portFlag, PORT, customer, callerNumber, calleeNumber, startDate, startTime, startAmPm, endDate, endTime, endAmPm );

        startDate = "02/04/2018";
        startTime = "12:01";
        startAmPm = "pm";

        endDate = "02/05/2018";
        endTime = "12:05";
        endAmPm = "pm";

        invokeMain( Project4.class, hostFlag, HOSTNAME, portFlag, PORT, customer, callerNumber, calleeNumber, startDate, startTime, startAmPm, endDate, endTime, endAmPm );

//        assertThat(out, out, containsString(Messages.definedWordAs(word, definition)));
//
//        result = invokeMain( Project4.class, HOSTNAME, PORT, word );
//        out = result.getTextWrittenToStandardOut();
//        assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));
//
//        result = invokeMain( Project4.class, HOSTNAME, PORT );
//        out = result.getTextWrittenToStandardOut();
//        assertThat(out, out, containsString(Messages.formatDictionaryEntry(word, definition)));
    }


    @Test
    public void test6AddOneCaller() {
        String customer = "Customer";
        String callerNumber = "123-456-7890";
        String calleeNumber = "234-567-8901";
        String startDate = "01/01/2018";
        String startTime = "12:34";
        String startAmPm = "pm";
        String endDate = "01/01/2018";
        String endTime = "2:35";
        String endAmPm = "pm";

        MainMethodResult result = invokeMain( Project4.class, hostFlag, HOSTNAME, portFlag, PORT, customer, callerNumber, calleeNumber, startDate, startTime, startAmPm, endDate, endTime, endAmPm );
    }

    @Test
    public void test7CommandLineArgs() {
        String customer = "Dave";
        String callerNumber = "503-245-2345";
        String calleeNumber = "765-389-1273";
        String startDate = "02/27/2018";
        String startTime = "8:56";
        String startAmPm = "am";
        String endDate = "02/27/2018";
        String endTime = "10:27";
        String endAmPm = "am";

        MainMethodResult result = invokeMain( Project4.class, hostFlag, HOSTNAME, portFlag, PORT, customer, callerNumber, calleeNumber, startDate, startTime, startAmPm, endDate, endTime, endAmPm );
    }

    @Test
    public void test8JustDave() {
        String customer = "Dave";

        MainMethodResult result = invokeMain( Project4.class, hostFlag, HOSTNAME, portFlag, PORT, customer);
    }
}
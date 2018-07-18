package edu.pdx.CS410J.tmassey;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project2} main class.
 */
public class Project2IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project1} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project2.class, args );
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getExitCode(), equalTo(0));
    }

    /**
     * Tests that invoking the main method with all arguments issues no error
     */
    @Test
    public void testAllCommandLineArguments() {
        MainMethodResult result = invokeMain("customer", "Tom Massey", "callerNumber", "503-550-5040", "calleeNumber", "503-608-2412", "startTime", "01/01/2018 13:04:00", "endTime", "01/01/2018 13:05:00");
        assertThat(result.getExitCode(), equalTo(1));
    }

    /**
     * Tests that invoking the main method with -textFile flag
     */
    @Test
    public void testTextFileFlagWritesThePhoneBill() {
        MainMethodResult result = invokeMain("customer", "Tom Massey", "callerNumber", "503-550-5040", "calleeNumber", "503-608-2412", "startTime", "01/01/2018 13:04:00", "endTime", "01/01/2018 13:05:00", "-textFile", "test");
        assertThat(result.getExitCode(), equalTo(1));
    }

}
package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project3} main class.
 */
public class Project3IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
    }

    /**
     * Tests that invoking the main method with no arguments issues an error
     */
    @Test
    public void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain(
                "-textFile",
                "tmassey/tmassey-test5.txt",
                "Project4",
                "123-456-7890",
                "234-567-8901",
                "03/03/2018",
                "09:16",
                "pm",
                "04/04/2018",
                "11:18",
                "pm",
                "-print");
        assertThat(result.getExitCode(), equalTo(1));
    }
}
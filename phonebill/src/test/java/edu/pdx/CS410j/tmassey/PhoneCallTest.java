package edu.pdx.cs410J.tmassey;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link PhoneCall} class.
 */
public class PhoneCallTest {

  @Test
  public void callerShouldBeSpecified() {
    var caller = "123-456-7890";
    var callee = "234-567-8901";
    var startTime = "01/2/2018 01:03 pm";
    var endTime = "01/2/2018 01:07 pm";

    PhoneCall call = new PhoneCall(caller, callee, new Date(startTime), new Date(endTime));
    assertThat(call.getCaller(), containsString("123-456-7890"));
  }

  @Test
  public void calleeShouldBeSpecified() {
    var caller = "123-456-7890";
    var callee = "234-567-8901";
    var startTime = "01/2/2018 1:03";
    var endTime = "01/2/2018 1:07";

    PhoneCall call = new PhoneCall(caller, callee, new Date(startTime), new Date(endTime));
    assertThat(call.getCallee(), containsString(callee));
  }

  @Test
  public void getStartTimeStringShouldBeReturned() {
    var caller = "123-456-7890";
    var callee = "234-567-8901";
    var startTime = "01/02/2018 01:01 AM";
    var endTime = "01/02/2018 01:11 AM";

    PhoneCall call = new PhoneCall(caller, callee, new Date(startTime), new Date(endTime));
    assertThat(call.getStartTimeString(), containsString(startTime));
  }
}
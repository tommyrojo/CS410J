package edu.pdx.cs410J.tmassey;

import org.junit.Test;

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
    var startTime = "01/2/2018 1:03";
    var endTime = "01/2/2018 1:07";

    PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);
    assertThat(call.getCaller(), containsString("123-456-7890"));
  }

  @Test
  public void calleeShouldBeSpecified() {
    var caller = "123-456-7890";
    var callee = "234-567-8901";
    var startTime = "01/2/2018 1:03";
    var endTime = "01/2/2018 1:07";

    PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);
    assertThat(call.getCallee(), containsString(callee));
  }

  @Test
  public void getStartTimeStringShouldBeReturned() {
    var caller = "123-456-7890";
    var callee = "234-567-8901";
    var startTime = "Jan 2, 2018";
    var endTime = "Jan 2, 2018";

    PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);
    assertThat(call.getStartTimeString(), containsString(startTime));
  }

  @Test
  public void getEndTimeStringShouldBeReturned() {
    var caller = "123-456-7890";
    var callee = "234-567-8901";
    var startTime = "Jan 2, 2018";
    var endTime = "Jan 2, 2018";

    PhoneCall call = new PhoneCall(caller, callee, startTime, endTime);
    assertThat(call.getEndTimeString(), containsString(endTime));
  }
}
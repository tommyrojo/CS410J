package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.DateFormat;
import java.util.Date;

/**
 * class to create a phone call
 */

public class PhoneCall extends AbstractPhoneCall implements Comparable<PhoneCall>{
  private final String caller;
  private final String callee;
  private Date startTime;
  private Date endTime;

  /**
   * PhoneCall Constructor to create a Phone Call object
   * @param caller
   * @param callee
   * @param startTime
   * @param endTime
   */
  public PhoneCall(String caller, String callee, String startTime, String endTime) {
    this.caller = caller;
    this.callee = callee;
    this.startTime = new Date(startTime);
    this.endTime = new Date(endTime);
  }

  /**
   * getCaller method
   * @return String caller
   */
  @Override
  public String getCaller() {
    return this.caller;
  }

  /**
   * getCallee method
   * @return String callee
   */
  @Override
  public String getCallee() {
    return this.callee;
  }

  /**
   * getStartTime method
   * @return Date startTime
   */

  @Override
  public Date getStartTime() { return this.startTime; }

  /**
   * getStartTimeString method
   * @return String startTime
   */
  @Override
  public String getStartTimeString() {
    var dateOut = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(startTime);

    return dateOut;
  }

  /**
   * getEndTime method
   * @return String endTime
  */
  @Override
  public Date getEndTime() { return this.endTime; }

  /**
   * getEndTimeString method
   * @return String endTime
   */
  @Override
  public String getEndTimeString() {
    var dateOut = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(endTime);

    return dateOut;
  };

  @Override
  public int compareTo(PhoneCall call) {
    if (this.startTime.after(call.startTime)) {
      return 1;
    } else if (this.startTime.before(call.startTime)) {
      return -1;
    } else {
      return this.caller.compareTo(call.caller);
    }
  }
}
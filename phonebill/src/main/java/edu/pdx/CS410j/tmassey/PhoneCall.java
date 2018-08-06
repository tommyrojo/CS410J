package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
  public PhoneCall(String caller, String callee, Date startTime, Date endTime) {
    this.caller = caller;
    this.callee = callee;
    this.startTime = startTime;
    this.endTime = endTime;
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
    return formatDate(this.startTime);
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
    return formatDate(this.endTime);
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

  private String formatDate(Date date) {
    return DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(date);
  }
}
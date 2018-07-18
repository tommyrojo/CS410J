package edu.pdx.cs410J.tmassey;

import edu.pdx.cs410J.AbstractPhoneCall;

/**
 * class to create a phone call
 */
public class PhoneCall extends AbstractPhoneCall {
  private final String caller;
  private final String callee;
  private String startTime;
  private String endTime;

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
   * getStartTimeString method
   * @return String startTime
   */
  @Override
  public String getStartTimeString() {
    return this.startTime;
  }

  /**
   * getEndTimeString method
   * @return String endTime
   */
  @Override
  public String getEndTimeString() {
    return this.endTime;
  };
}
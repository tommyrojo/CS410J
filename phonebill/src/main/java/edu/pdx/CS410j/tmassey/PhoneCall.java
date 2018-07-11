package edu.pdx.CS410j.tmassey;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
  private final String caller;
  private final String callee;
  private String startTime;
  private String endTime;

  public PhoneCall(String caller, String callee, String startTime, String endTime) {
    this.caller = caller;
    this.callee = callee;
    this.startTime = startTime;
    this.endTime = endTime;
  }

  @Override
  public String getCaller() {
    return this.caller;
  }

  @Override
  public String getCallee() {
    return this.callee;
  }

  @Override
  public String getStartTimeString() {
    return this.startTime;
  }

  @Override
  public String getEndTimeString() {
    return this.endTime;
  };
}

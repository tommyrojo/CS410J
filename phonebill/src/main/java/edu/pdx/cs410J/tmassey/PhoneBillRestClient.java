package edu.pdx.cs410J.tmassey;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.util.Date;

import static java.net.HttpURLConnection.HTTP_NOT_FOUND;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class PhoneBillRestClient extends HttpRequestHelper
{
    private static final String WEB_APP = "phonebill";
    private static final String SERVLET = "calls";

    private final String url;


    /**
     * Creates a client to the Phone Bil REST service running on the given host and port
     * @param hostName The name of the host
     * @param port The port
     */
    public PhoneBillRestClient( String hostName, int port )
    {
        this.url = String.format( "http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET );
    }

    public PhoneBillRestClient() {
        this.url = "";
    }

    public void addDictionaryEntry(String word, String definition) throws IOException {
      Response response = postToMyURL("word", word, "definition", definition);
      throwExceptionIfNotOkayHttpStatus(response);
    }

    @VisibleForTesting
    Response postToMyURL(String... dictionaryEntries) throws IOException {
      return post(this.url, dictionaryEntries);
    }

    public void removeAllDictionaryEntries() throws IOException {
      Response response = delete(this.url);
      throwExceptionIfNotOkayHttpStatus(response);
    }

    private Response throwExceptionIfNotOkayHttpStatus(Response response) {
      int code = response.getCode();

      if (code == HTTP_NOT_FOUND ) {
          String customer = response.getContent();
          throw new NoSuchPhoneBillException(customer);
      }

      if (code != HTTP_OK) {
        throw new PhoneBillRestException(code);
      }
      return response;
    }

    public void removeAllPhoneBills() throws IOException {
        Response response = delete(this.url);
        throwExceptionIfNotOkayHttpStatus(response);
    }

    public String searchPhoneBill(String customerName, String startTime, String endTime) throws IOException {
        String [] searchParameters = {
          "customer", customerName,
          "startTime", startTime,
          "endTime", endTime
        };

        var response = get(this.url, searchParameters);
        throwExceptionIfNotOkayHttpStatus(response);
        return response.getContent();
    }

    public void addPhoneCall(String customerName, PhoneCall call) throws IOException {
        String[] postParamters = {
                "customer", customerName,
                "caller", call.getCaller(),
                "callee", call.getCallee(),
                "startTime", String.valueOf(call.getStartTime().getTime()),
                "endTime", String.valueOf(call.getEndTime().getTime())
        };
        Response response = postToMyURL(postParamters);
        throwExceptionIfNotOkayHttpStatus(response);
    }

    public String getPrettyPhoneBill(String customerName) throws IOException {
        Response response = get(this.url, "customer", customerName);

        throwExceptionIfNotOkayHttpStatus(response);

        return response.getContent();
    }

    private class PhoneBillRestException extends RuntimeException {
      public PhoneBillRestException(int httpStatusCode) {
        super("Got an HTTP Status Code of " + httpStatusCode);
      }
    }

}

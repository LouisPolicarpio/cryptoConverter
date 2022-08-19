package model.API.outputAPI;

import org.apache.http.auth.AuthenticationException;

import java.io.IOException;

public interface OutputAPI {
    public void postEmail(String text, int selectedCur, int compareCur, String rate, String total) throws AuthenticationException, IOException;
}

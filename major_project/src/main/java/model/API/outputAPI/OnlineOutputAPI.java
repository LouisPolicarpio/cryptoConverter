package model.API.outputAPI;

import model.API.Http;
import org.apache.http.auth.AuthenticationException;

import java.io.IOException;

public class OnlineOutputAPI implements OutputAPI{
    @Override
    public void postEmail(String text, int selectedCur, int compareCur, String rate, String total) throws AuthenticationException, IOException {
        Http.postEmail( text,  selectedCur,  compareCur,  rate,  total);
    }
}

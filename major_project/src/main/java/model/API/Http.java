package model.API;

import model.email.*;
import com.google.gson.Gson;
import model.Status;
import model.conversion.ConversionResp;
import model.conversion.Quote;
import model.cryptoInfo.CryptoInfoResp;
import model.cryptoInfo.Info;
import model.cryptoMap.CryptoMapResp;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.StringEntity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Http {

    //private static String INPUT_API_KEY = "d7a9a36a-6dea-4367-9bdf-2588fd0a60e7";
    private static String INPUT_API_KEY =  System.getenv("INPUT_API_KEY");
    // private static String SENDGRID_API_KEY = "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";
    private static String SENDGRID_API_KEY = System.getenv("SENDGRID_API_KEY");

    private static String SENDGRID_API_EMAIL = System.getenv("SENDGRID_API_EMAIL");

    public static void main(String[] args) throws AuthenticationException, IOException {
       System.out.println(INPUT_API_KEY);
        System.out.println(SENDGRID_API_KEY);
        System.out.println(SENDGRID_API_EMAIL);

    }

    public static ConversionResp getConversion(float amount, String id, String convertID){
        String uri = "https://pro-api.coinmarketcap.com/v2/tools/price-conversion";
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("amount", Float.toString(amount)));
        parameters.add(new BasicNameValuePair("id", id));
        parameters.add(new BasicNameValuePair("convert_id", convertID));
        String result =  handleApiCall(uri, parameters);
        System.out.println(result);
        return getConversionJSON(result,convertID);
    }

    public static ConversionResp getConversionJSON(String result, String convertID){
        Gson gson = new Gson();
        //create map of Json
        Map map = gson.fromJson(result, Map.class);
        Map statusMap = (Map) map.get("status");

        //create map of status (from json) and convert to Status object
        String jsonStatus = gson.toJson(statusMap);
        Status status = gson.fromJson (jsonStatus, Status.class);

        Map dataMap = (Map) map.get("data");
        Map quoteMap =  (Map) ((Map) dataMap.get("quote")).get(convertID);
        String quoteJSON = gson.toJson(quoteMap);



        Quote quote  = gson.fromJson (quoteJSON, Quote.class);

        ConversionResp resp = new ConversionResp(status, quote);

        return resp;
    }


    public static CryptoMapResp getActiveCrypto() {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/map";
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("listing_status", "active"));
        parameters.add(new BasicNameValuePair("start", "1"));
        parameters.add(new BasicNameValuePair("limit", "5000 "));
        String result = handleApiCall(uri, parameters);


        //not tested beacuse it is not my logic
        Gson gson = new Gson();
        return gson.fromJson(result, CryptoMapResp.class);


    }



    public static CryptoInfoResp getCryptoInfo(String cryptoID) {
        String uri = "https://pro-api.coinmarketcap.com/v2/cryptocurrency/info";
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("id", cryptoID));
        String result = handleApiCall(uri, parameters);

        return   getCryptoInfoJSON(result, cryptoID);
    }

    public static CryptoInfoResp getCryptoInfoJSON(String result, String cryptoID) {
        Gson gson = new Gson();

        //create map of Json
        Map map = gson.fromJson(result, Map.class);
        Map statusMap = (Map) map.get("status");

        //create map of status (from json) and convert to Status object
        String jsonStatus = gson.toJson(statusMap);
        Status status = gson.fromJson (jsonStatus, Status.class);

        //create map of data (from json), them make a map of cryptos (from dataMap) and convert Info class
        Map dataMap = (Map) map.get("data");
        Map cryptoMap = (Map) dataMap.get(cryptoID);
        String jsonInfo = gson.toJson(cryptoMap);
        System.out.println(jsonInfo);
        Info info =gson.fromJson (jsonInfo, Info.class);

        CryptoInfoResp resp = new CryptoInfoResp(info , status);
        return resp;
    }


        //based on https://coinmarketcap.com/api/documentation/v1/#section/Introduction quick Start guide java
    public static String handleApiCall(String uri, List<NameValuePair> parameters) {
        String result = "";
        try {
            result = makeAPICall(uri, parameters);

        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }


    //based on https://coinmarketcap.com/api/documentation/v1/#section/Introduction quick Start guide java
    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());
        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", INPUT_API_KEY);
        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);

            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }

    //https://www.baeldung.com/httpclient-post-http-request
    public static void postEmail(String emailTo, int selectedCur, int compareCur, String rate, String total)
            throws ClientProtocolException, IOException, AuthenticationException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("https://api.sendgrid.com/v3/mail/send");


        CryptoInfoResp selectedResp = Http.getCryptoInfo(String.valueOf(selectedCur));
            Info  selectedInfo = selectedResp.getData();


        CryptoInfoResp compareResp = Http.getCryptoInfo(String.valueOf(compareCur));
            Info  compareInfo = compareResp.getData();


        String emailBody =  MakeReadable( selectedInfo, compareInfo, rate,  total);

        To to = new To(emailTo,"Customer");
        Personalizations personalizations = new Personalizations(new To[]{to},"Currency Conversion");
        Content content = new Content("text/html", emailBody);
        From from = new From(SENDGRID_API_EMAIL,"Currency Converter");
        Reply_to reply_to = new Reply_to(SENDGRID_API_EMAIL,"Currency Converter");
        Email email = new Email(new Personalizations[] {personalizations},"Currency Converter", new Content[]{content}, from, reply_to);

        String JsonString = new Gson().toJson(email);
        StringEntity entity = new StringEntity(JsonString);

        httpPost.setEntity(entity);
        httpPost.setHeader("Authorization", "Bearer "+ SENDGRID_API_KEY );
        httpPost.setHeader("Content-Type", "application/json");

        CloseableHttpResponse response = client.execute(httpPost);
        System.out.println(response);



        client.close();
    }

    public static String MakeReadable( Info  selectedInfo, Info compareInfo,String rate, String total){
        String res = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    <div style=\"font-family:tahoma; padding:50px\" ;>\n" +
                "        <div\n" +
                "            style=\"background-color:#21234a; color:white; padding:10px; height:300px; background-image:url(https://phantom-marca.unidadeditorial.es/93928cf77160ea6f36a06e964cc0d96d/resize/1320/f/jpg/assets/multimedia/imagenes/2022/05/13/16523961253322.jpg);background-repeat: no-repeat; background-attachment:fixed; background-size: 100% 100%;\">\n" +
                "            <h1>Currency Converter</h1>\n" +
                "        </div>\n" +
                "        <div style=\"background-color: #e3eeff;  padding:30px\">\n" +
                "            <h2>Selected Currency: "+  selectedInfo.getName() +"</h2>\n" +
                "            <h4>symbol: "+selectedInfo.getSymbol()+"</h4>\n" +
                "            <h4>Description</h4> <p>" +selectedInfo.getDescription()+
                "            </p>\n" +
                "            <h4>date launched: "+selectedInfo.getDate_launched()+"</h4>\n" +
                "            <h4> website:\n " +
                "                   <a href='"+ selectedInfo.getUrls().getWebsite()[0]+"'>" + selectedInfo.getUrls().getWebsite()[0]+ "</a>"+
                "            </h4>\n" +
                "        </div>\n" +
                "        <div style=\" padding:30px\">\n" +
                "            <h2>Compared to Currency: "+compareInfo.getName() + "\n"+"</h2>\n" +
                "            <h4>symbol: "+compareInfo.getSymbol()+"</h4>\n" +
                "            <h4>Description</h4>\n" +
                "            <p>"+compareInfo.getDescription()+ "</p>\n" +
                "            <h4>date launched: "+ compareInfo.getDate_launched()+" </h4>\n" +
                "            <h4> website:\n" +
                "                     <a href='"+ compareInfo.getUrls().getWebsite()[0]+"'>" +compareInfo.getUrls().getWebsite()[0] +"</a>"+
                "            </h4>\n" +
                "        </div>\n" +
                "        <div style=\"background-color:#21234a; padding:40px; text-align:center\">\n" +
                "            <h2 style=\"color:white\">Results</h2>\n" +
                "            <h2 style=\"color:white\">"+ rate +"<h2>\n" +
                "                    <h2 style=\"color:white\">"+ total +"<h2>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";


        return res;
    }


}

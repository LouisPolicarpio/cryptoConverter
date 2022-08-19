import com.google.gson.JsonSyntaxException;
import model.Status;
import model.conversion.ConversionResp;
import model.cryptoInfo.CryptoInfoResp;
import model.cryptoInfo.Info;
import model.cryptoInfo.Urls;
import org.junit.jupiter.api.Test;
import view.windows.ConvertResultsWindow;
import model.API.Http;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpJSONTests {

    @Test
    public void isPosNumberNegitve() {
        assertThat(ConvertResultsWindow.isPosNumber(String.valueOf(-1)),equalTo(false) );
    }

    @Test
    public void isPosNumber0() {
        assertThat(ConvertResultsWindow.isPosNumber(String.valueOf(0)),equalTo(false) );
    }

    @Test
    public void isPosNumberpositive() {
        assertThat(ConvertResultsWindow.isPosNumber(String.valueOf(100)),equalTo(true) );
    }

    @Test
    public void isPosNumberfloat() {
        assertThat(ConvertResultsWindow.isPosNumber(String.valueOf(1.1)),equalTo(true) );
    }


    @Test
    public void isPosNumberfloatnegative() {
        assertThat(ConvertResultsWindow.isPosNumber(String.valueOf(-1.1)),equalTo(false) );
    }

    @Test
    public void getCryptoInfoJSON_NameTest() {

        Status status = new Status("2022-05-12T04:09:17.465Z", 0, "null",20,1,"null");

        Urls url = new Urls("https://blackcoin.org");
        Info infos = new Info(url,"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png","170","BlackCoin","BLK",
                "BlackCoin (BLK) is a cryptocurrency launched in 2014." +
                "BlackCoin has a current supply of 61,360,920.34627817. The last known price of BlackCoin is 0.02189377"+
                "USD and is down -5.27 over the last 24 hours. It is currently trading on 4 active market(s) with" +
                "$2,167.80 traded over the last 24 hours. More information can be found at https://blackcoin.org/.","2014-02-28T00:00:00.000Z"
                );

        CryptoInfoResp resp = new CryptoInfoResp(infos,status);

        CryptoInfoResp infoResp = Http.getCryptoInfoJSON("{\"status\":{\"timestamp\":\"2022-05-12T04:49:34.664Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":18,\"credit_count\":1,\"notice\":null},\"data\":{\"170\":{\"id\":170,\"name\":\"BlackCoin\",\"symbol\":\"BLK\",\"category\":\"coin\",\"description\":\"BlackCoin (BLK) is a cryptocurrency launched in 2014. BlackCoin has a current supply of 61,361,085.34627817. The last known price of BlackCoin is 0.02029824 USD and is down -12.28 over the last 24 hours. It is currently trading on 4 active market(s) with $2,104.34 traded over the last 24 hours. More information can be found at https://blackcoin.org/.\",\"slug\":\"blackcoin\",\"logo\":\"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png\",\"subreddit\":\"blackcoin\",\"notice\":\"\",\"tags\":[\"pos\",\"scrypt\"],\"tag-names\":[\"PoS\",\"Scrypt\"],\"tag-groups\":[\"ALGORITHM\",\"ALGORITHM\"],\"urls\":{\"website\":[\"https://blackcoin.org/\",\"https://blackcoinmore.org\"],\"twitter\":[\"https://twitter.com/BlackcoinOrg\"],\"message_board\":[\"https://blackcoin.nl\"],\"chat\":[\"https://discord.gg/hjNUgWD\",\"https://t.me/blackcoinNL\"],\"facebook\":[],\"explorer\":[\"https://chainz.cryptoid.info/blk/\",\"https://bitinfocharts.com/blackcoin\",\"https://blk.tokenview.com/\"],\"reddit\":[\"https://reddit.com/r/blackcoin\"],\"technical_doc\":[\"https://blackcoin.co/blackcoin-pos-protocol-v2-whitepaper.pdf\"],\"source_code\":[\"https://gitlab.com/blackcoin\"],\"announcement\":[\"https://bitcointalk.org/index.php?topic=3017838.0\"]},\"platform\":null,\"date_added\":\"2014-02-28T00:00:00.000Z\",\"twitter_username\":\"BlackcoinOrg\",\"is_hidden\":0,\"date_launched\":\"2014-02-24T00:00:00.000Z\",\"contract_address\":[],\"self_reported_circulating_supply\":null,\"self_reported_tags\":null,\"self_reported_market_cap\":null}}}"
            , "170");

        assertThat(resp.getData().getName() , equalTo(infoResp.getData().getName()));
    }

    @Test
    public void getCryptoInfoJSON_descTest() {

        Status status = new Status("2022-05-12T04:09:17.465Z", 0, "null",20,1,"null");

        Urls url = new Urls("https://blackcoin.org");
        Info infos = new Info(url,"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png","170","BlackCoin","BLK","BlackCoin (BLK) is a cryptocurrency launched in 2014. BlackCoin has a current supply of 61,361,085.34627817. The last known price of BlackCoin is 0.02029824 USD and is down -12.28 over the last 24 hours. It is currently trading on 4 active market(s) with $2,104.34 traded over the last 24 hours. More information can be found at https://blackcoin.org/."
                ,"2014-02-28T00:00:00.000Z"
                );

        CryptoInfoResp resp = new CryptoInfoResp(infos,status);

        CryptoInfoResp infoResp = Http.getCryptoInfoJSON("{\"status\":{\"timestamp\":\"2022-05-12T04:49:34.664Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":18,\"credit_count\":1,\"notice\":null},\"data\":{\"170\":{\"id\":170,\"name\":\"BlackCoin\",\"symbol\":\"BLK\",\"category\":\"coin\",\"description\":\"BlackCoin (BLK) is a cryptocurrency launched in 2014. BlackCoin has a current supply of 61,361,085.34627817. The last known price of BlackCoin is 0.02029824 USD and is down -12.28 over the last 24 hours. It is currently trading on 4 active market(s) with $2,104.34 traded over the last 24 hours. More information can be found at https://blackcoin.org/.\",\"slug\":\"blackcoin\",\"logo\":\"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png\",\"subreddit\":\"blackcoin\",\"notice\":\"\",\"tags\":[\"pos\",\"scrypt\"],\"tag-names\":[\"PoS\",\"Scrypt\"],\"tag-groups\":[\"ALGORITHM\",\"ALGORITHM\"],\"urls\":{\"website\":[\"https://blackcoin.org/\",\"https://blackcoinmore.org\"],\"twitter\":[\"https://twitter.com/BlackcoinOrg\"],\"message_board\":[\"https://blackcoin.nl\"],\"chat\":[\"https://discord.gg/hjNUgWD\",\"https://t.me/blackcoinNL\"],\"facebook\":[],\"explorer\":[\"https://chainz.cryptoid.info/blk/\",\"https://bitinfocharts.com/blackcoin\",\"https://blk.tokenview.com/\"],\"reddit\":[\"https://reddit.com/r/blackcoin\"],\"technical_doc\":[\"https://blackcoin.co/blackcoin-pos-protocol-v2-whitepaper.pdf\"],\"source_code\":[\"https://gitlab.com/blackcoin\"],\"announcement\":[\"https://bitcointalk.org/index.php?topic=3017838.0\"]},\"platform\":null,\"date_added\":\"2014-02-28T00:00:00.000Z\",\"twitter_username\":\"BlackcoinOrg\",\"is_hidden\":0,\"date_launched\":\"2014-02-24T00:00:00.000Z\",\"contract_address\":[],\"self_reported_circulating_supply\":null,\"self_reported_tags\":null,\"self_reported_market_cap\":null}}}"
                , "170");


        assertThat(resp.getData().getDescription() , equalTo(infoResp.getData().getDescription()));
    }

    @Test
    public void getCryptoInfoJSON_idTest() {

        Status status = new Status("2022-05-12T04:09:17.465Z", 0, "null",20,1,"null");

        Urls url = new Urls("https://blackcoin.org");
        Info infos = new Info(url,"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png","170.0","BlackCoin","BLK",
                "BlackCoin (BLK) is a cryptocurrency launched in 2014." +
                        "BlackCoin has a current supply of 61,360,920.34627817. The last known price of BlackCoin is 0.02189377"+
                        "USD and is down -5.27 over the last 24 hours. It is currently trading on 4 active market(s) with" +
                        "$2,167.80 traded over the last 24 hours. More information can be found at https://blackcoin.org/.","2014-02-28T00:00:00.000Z"
      );

        CryptoInfoResp resp = new CryptoInfoResp(infos,status);

        CryptoInfoResp infoResp = Http.getCryptoInfoJSON("{\"status\":{\"timestamp\":\"2022-05-12T04:49:34.664Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":18,\"credit_count\":1,\"notice\":null},\"data\":{\"170\":{\"id\":170,\"name\":\"BlackCoin\",\"symbol\":\"BLK\",\"category\":\"coin\",\"description\":\"BlackCoin (BLK) is a cryptocurrency launched in 2014. BlackCoin has a current supply of 61,361,085.34627817. The last known price of BlackCoin is 0.02029824 USD and is down -12.28 over the last 24 hours. It is currently trading on 4 active market(s) with $2,104.34 traded over the last 24 hours. More information can be found at https://blackcoin.org/.\",\"slug\":\"blackcoin\",\"logo\":\"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png\",\"subreddit\":\"blackcoin\",\"notice\":\"\",\"tags\":[\"pos\",\"scrypt\"],\"tag-names\":[\"PoS\",\"Scrypt\"],\"tag-groups\":[\"ALGORITHM\",\"ALGORITHM\"],\"urls\":{\"website\":[\"https://blackcoin.org/\",\"https://blackcoinmore.org\"],\"twitter\":[\"https://twitter.com/BlackcoinOrg\"],\"message_board\":[\"https://blackcoin.nl\"],\"chat\":[\"https://discord.gg/hjNUgWD\",\"https://t.me/blackcoinNL\"],\"facebook\":[],\"explorer\":[\"https://chainz.cryptoid.info/blk/\",\"https://bitinfocharts.com/blackcoin\",\"https://blk.tokenview.com/\"],\"reddit\":[\"https://reddit.com/r/blackcoin\"],\"technical_doc\":[\"https://blackcoin.co/blackcoin-pos-protocol-v2-whitepaper.pdf\"],\"source_code\":[\"https://gitlab.com/blackcoin\"],\"announcement\":[\"https://bitcointalk.org/index.php?topic=3017838.0\"]},\"platform\":null,\"date_added\":\"2014-02-28T00:00:00.000Z\",\"twitter_username\":\"BlackcoinOrg\",\"is_hidden\":0,\"date_launched\":\"2014-02-24T00:00:00.000Z\",\"contract_address\":[],\"self_reported_circulating_supply\":null,\"self_reported_tags\":null,\"self_reported_market_cap\":null}}}"
                , "170");

        assertThat(resp.getData().getId() , equalTo(infoResp.getData().getId()));
    }



    @Test
    public void getCryptoInfoJSON_Date_launchedTest() {

        Status status = new Status("2022-05-12T04:09:17.465Z", 0, "null",20,1,"null");

        Urls url = new Urls("https://blackcoin.org");
        Info infos = new Info(url,"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png","170","BlackCoin","BLK",
                "BlackCoin (BLK) is a cryptocurrency launched in 2014." +
                        "BlackCoin has a current supply of 61,360,920.34627817. The last known price of BlackCoin is 0.02189377"+
                        "USD and is down -5.27 over the last 24 hours. It is currently trading on 4 active market(s) with" +
                        "$2,167.80 traded over the last 24 hours. More information can be found at https://blackcoin.org/.","2014-02-24T00:00:00.000Z"
                );

        CryptoInfoResp resp = new CryptoInfoResp(infos,status);

        CryptoInfoResp infoResp = Http.getCryptoInfoJSON("{\"status\":{\"timestamp\":\"2022-05-12T04:49:34.664Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":18,\"credit_count\":1,\"notice\":null},\"data\":{\"170\":{\"id\":170,\"name\":\"BlackCoin\",\"symbol\":\"BLK\",\"category\":\"coin\",\"description\":\"BlackCoin (BLK) is a cryptocurrency launched in 2014. BlackCoin has a current supply of 61,361,085.34627817. The last known price of BlackCoin is 0.02029824 USD and is down -12.28 over the last 24 hours. It is currently trading on 4 active market(s) with $2,104.34 traded over the last 24 hours. More information can be found at https://blackcoin.org/.\",\"slug\":\"blackcoin\",\"logo\":\"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png\",\"subreddit\":\"blackcoin\",\"notice\":\"\",\"tags\":[\"pos\",\"scrypt\"],\"tag-names\":[\"PoS\",\"Scrypt\"],\"tag-groups\":[\"ALGORITHM\",\"ALGORITHM\"],\"urls\":{\"website\":[\"https://blackcoin.org/\",\"https://blackcoinmore.org\"],\"twitter\":[\"https://twitter.com/BlackcoinOrg\"],\"message_board\":[\"https://blackcoin.nl\"],\"chat\":[\"https://discord.gg/hjNUgWD\",\"https://t.me/blackcoinNL\"],\"facebook\":[],\"explorer\":[\"https://chainz.cryptoid.info/blk/\",\"https://bitinfocharts.com/blackcoin\",\"https://blk.tokenview.com/\"],\"reddit\":[\"https://reddit.com/r/blackcoin\"],\"technical_doc\":[\"https://blackcoin.co/blackcoin-pos-protocol-v2-whitepaper.pdf\"],\"source_code\":[\"https://gitlab.com/blackcoin\"],\"announcement\":[\"https://bitcointalk.org/index.php?topic=3017838.0\"]},\"platform\":null,\"date_added\":\"2014-02-28T00:00:00.000Z\",\"twitter_username\":\"BlackcoinOrg\",\"is_hidden\":0,\"date_launched\":\"2014-02-24T00:00:00.000Z\",\"contract_address\":[],\"self_reported_circulating_supply\":null,\"self_reported_tags\":null,\"self_reported_market_cap\":null}}}"
                , "170");


        assertThat(resp.getData().getDate_launched() , equalTo(infoResp.getData().getDate_launched()));
    }


    @Test
    public void getCryptoInfoJSON_logoTest() {

        Status status = new Status("2022-05-12T04:09:17.465Z", 0, "null",20,1,"null");

        Urls url = new Urls("https://blackcoin.org");
        Info infos = new Info(url,"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png","170","BlackCoin","BLK",
                "BlackCoin (BLK) is a cryptocurrency launched in 2014." +
                        "BlackCoin has a current supply of 61,360,920.34627817. The last known price of BlackCoin is 0.02189377"+
                        "USD and is down -5.27 over the last 24 hours. It is currently trading on 4 active market(s) with" +
                        "$2,167.80 traded over the last 24 hours. More information can be found at https://blackcoin.org/.","2014-02-24T00:00:00.000Z"
                );

        CryptoInfoResp resp = new CryptoInfoResp(infos,status);

        CryptoInfoResp infoResp = Http.getCryptoInfoJSON("{\"status\":{\"timestamp\":\"2022-05-12T04:49:34.664Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":18,\"credit_count\":1,\"notice\":null},\"data\":{\"170\":{\"id\":170,\"name\":\"BlackCoin\",\"symbol\":\"BLK\",\"category\":\"coin\",\"description\":\"BlackCoin (BLK) is a cryptocurrency launched in 2014. BlackCoin has a current supply of 61,361,085.34627817. The last known price of BlackCoin is 0.02029824 USD and is down -12.28 over the last 24 hours. It is currently trading on 4 active market(s) with $2,104.34 traded over the last 24 hours. More information can be found at https://blackcoin.org/.\",\"slug\":\"blackcoin\",\"logo\":\"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png\",\"subreddit\":\"blackcoin\",\"notice\":\"\",\"tags\":[\"pos\",\"scrypt\"],\"tag-names\":[\"PoS\",\"Scrypt\"],\"tag-groups\":[\"ALGORITHM\",\"ALGORITHM\"],\"urls\":{\"website\":[\"https://blackcoin.org/\",\"https://blackcoinmore.org\"],\"twitter\":[\"https://twitter.com/BlackcoinOrg\"],\"message_board\":[\"https://blackcoin.nl\"],\"chat\":[\"https://discord.gg/hjNUgWD\",\"https://t.me/blackcoinNL\"],\"facebook\":[],\"explorer\":[\"https://chainz.cryptoid.info/blk/\",\"https://bitinfocharts.com/blackcoin\",\"https://blk.tokenview.com/\"],\"reddit\":[\"https://reddit.com/r/blackcoin\"],\"technical_doc\":[\"https://blackcoin.co/blackcoin-pos-protocol-v2-whitepaper.pdf\"],\"source_code\":[\"https://gitlab.com/blackcoin\"],\"announcement\":[\"https://bitcointalk.org/index.php?topic=3017838.0\"]},\"platform\":null,\"date_added\":\"2014-02-28T00:00:00.000Z\",\"twitter_username\":\"BlackcoinOrg\",\"is_hidden\":0,\"date_launched\":\"2014-02-24T00:00:00.000Z\",\"contract_address\":[],\"self_reported_circulating_supply\":null,\"self_reported_tags\":null,\"self_reported_market_cap\":null}}}"
                , "170");


        assertThat(resp.getData().getLogo() , equalTo(infoResp.getData().getLogo()));
    }

    @Test
    public void getCryptoInfoJSON_SymbolTest() {

        Status status = new Status("2022-05-12T04:09:17.465Z", 0, "null",20,1,"null");

        Urls url = new Urls("https://blackcoin.org/");
        Info infos = new Info(url,"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png","170","BlackCoin","BLK",
                "BlackCoin (BLK) is a cryptocurrency launched in 2014." +
                        "BlackCoin has a current supply of 61,360,920.34627817. The last known price of BlackCoin is 0.02189377"+
                        "USD and is down -5.27 over the last 24 hours. It is currently trading on 4 active market(s) with" +
                        "$2,167.80 traded over the last 24 hours. More information can be found at https://blackcoin.org/.","2014-02-24T00:00:00.000Z"
        );

        CryptoInfoResp resp = new CryptoInfoResp(infos,status);

        CryptoInfoResp infoResp = Http.getCryptoInfoJSON("{\"status\":{\"timestamp\":\"2022-05-12T04:49:34.664Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":18,\"credit_count\":1,\"notice\":null},\"data\":{\"170\":{\"id\":170,\"name\":\"BlackCoin\",\"symbol\":\"BLK\",\"category\":\"coin\",\"description\":\"BlackCoin (BLK) is a cryptocurrency launched in 2014. BlackCoin has a current supply of 61,361,085.34627817. The last known price of BlackCoin is 0.02029824 USD and is down -12.28 over the last 24 hours. It is currently trading on 4 active market(s) with $2,104.34 traded over the last 24 hours. More information can be found at https://blackcoin.org/.\",\"slug\":\"blackcoin\",\"logo\":\"https://s2.coinmarketcap.com/static/img/coins/64x64/170.png\",\"subreddit\":\"blackcoin\",\"notice\":\"\",\"tags\":[\"pos\",\"scrypt\"],\"tag-names\":[\"PoS\",\"Scrypt\"],\"tag-groups\":[\"ALGORITHM\",\"ALGORITHM\"],\"urls\":{\"website\":[\"https://blackcoin.org/\",\"https://blackcoinmore.org\"],\"twitter\":[\"https://twitter.com/BlackcoinOrg\"],\"message_board\":[\"https://blackcoin.nl\"],\"chat\":[\"https://discord.gg/hjNUgWD\",\"https://t.me/blackcoinNL\"],\"facebook\":[],\"explorer\":[\"https://chainz.cryptoid.info/blk/\",\"https://bitinfocharts.com/blackcoin\",\"https://blk.tokenview.com/\"],\"reddit\":[\"https://reddit.com/r/blackcoin\"],\"technical_doc\":[\"https://blackcoin.co/blackcoin-pos-protocol-v2-whitepaper.pdf\"],\"source_code\":[\"https://gitlab.com/blackcoin\"],\"announcement\":[\"https://bitcointalk.org/index.php?topic=3017838.0\"]},\"platform\":null,\"date_added\":\"2014-02-28T00:00:00.000Z\",\"twitter_username\":\"BlackcoinOrg\",\"is_hidden\":0,\"date_launched\":\"2014-02-24T00:00:00.000Z\",\"contract_address\":[],\"self_reported_circulating_supply\":null,\"self_reported_tags\":null,\"self_reported_market_cap\":null}}}"
                , "170");

        assertThat(resp.getData().getUrls().getWebsite()[0] , equalTo(infoResp.getData().getUrls().getWebsite()[0]));
    }


    @Test
    public void getCryptoInfoJSON_invalidJSON() {
        assertThrows(JsonSyntaxException.class, () -> {
            Http.getCryptoInfoJSON("sadasdasdasdasd", "170");
        });

    }

    @Test
    public void MakeReadableTestCorrect() {
        Urls urls = new Urls("https://www.google.com/");
        Info selectedInfo = new Info(urls, "https://picsum.photos/200", "1", "name1", "symbol", "desc", "launch");
        Info compareInfo = new Info(urls, "https://picsum.photos/200", "2", "name2", "symbol", "desc", "launch");
        String rate = "1234";
        String total = "6790";

        String res = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    <div style=\"font-family:tahoma; padding:50px\" ;>\n" +
                "        <div\n" +
                "            style=\"background-color:#21234a; color:white; padding:10px; height:300px; background-image:url(https://phantom-marca.unidadeditorial.es/93928cf77160ea6f36a06e964cc0d96d/resize/1320/f/jpg/assets/multimedia/imagenes/2022/05/13/16523961253322.jpg);background-repeat: no-repeat; background-attachment:fixed; background-size: 100% 100%;\">\n" +
                "            <h1>Currency Converter</h1>\n" +
                "        </div>\n" +
                "        <div style=\"background-color: #e3eeff;  padding:30px\">\n" +
                "            <h2>Selected Currency: name1</h2>\n" +
                "            <h4>symbol: symbol</h4>\n" +
                "            <h4>Description</h4> <p>desc            </p>\n" +
                "            <h4>date launched: launch</h4>\n" +
                "            <h4> website:\n" +
                "                    <a href='https://www.google.com/'>https://www.google.com/</a>            </h4>\n" +
                "        </div>\n" +
                "        <div style=\" padding:30px\">\n" +
                "            <h2>Compared to Currency: name2\n" +
                "</h2>\n" +
                "            <h4>symbol: symbol</h4>\n" +
                "            <h4>Description</h4>\n" +
                "            <p>desc</p>\n" +
                "            <h4>date launched: launch </h4>\n" +
                "            <h4> website:\n" +
                "                     <a href='https://www.google.com/'>https://www.google.com/</a>            </h4>\n" +
                "        </div>\n" +
                "        <div style=\"background-color:#21234a; padding:40px; text-align:center\">\n" +
                "            <h2 style=\"color:white\">Results</h2>\n" +
                "            <h2 style=\"color:white\">1234<h2>\n" +
                "                    <h2 style=\"color:white\">6790<h2>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

       assertThat(Http.MakeReadable(selectedInfo, compareInfo, rate ,total),equalTo(res));
    }
    @Test
    public void getConversionJSONFail_BadJSON() {
        String result = "\"status\":{\"timestamp\":\"2022-05-13T03:14:59.888Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":33,\"credit_count\":1,\"notice\":null},\"data\":{\"id\":1,\"symbol\":\"BTC\",\"name\":\"Bitcoin\",\"amount\":1,\"last_updated\":\"2022-05-13T03:14:00.000Z\",\"quote\":{\"2\":{\"price\":447.4548698400249,\"last_updated\":\"2022-05-13T03:14:00.000Z\"}}}}";
        String convertID = "2";
        assertThrows(JsonSyntaxException.class, () -> {
            Http.getConversionJSON(result,convertID);
        });
    }


    @Test
    public void getConversionJSONSucess_price() {
        String result = "{\"status\":{\"timestamp\":\"2022-05-13T03:14:59.888Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":33,\"credit_count\":1,\"notice\":null},\"data\":{\"id\":1,\"symbol\":\"BTC\",\"name\":\"Bitcoin\",\"amount\":1,\"last_updated\":\"2022-05-13T03:14:00.000Z\",\"quote\":{\"2\":{\"price\":447.4548698400249,\"last_updated\":\"2022-05-13T03:14:00.000Z\"}}}}";
        String convertID = "2";
        ConversionResp resp = Http.getConversionJSON(result,convertID);
        assertThat(resp.getQuote().getPrice(),equalTo((float)447.45486));
    }
    @Test
    public void getConversionJSONSucess_Last_updated() {
        String result = "{\"status\":{\"timestamp\":\"2022-05-13T03:14:59.888Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":33,\"credit_count\":1,\"notice\":null},\"data\":{\"id\":1,\"symbol\":\"BTC\",\"name\":\"Bitcoin\",\"amount\":1,\"last_updated\":\"2022-05-13T03:14:00.000Z\",\"quote\":{\"2\":{\"price\":447.4548698400249,\"last_updated\":\"2022-05-13T03:14:00.000Z\"}}}}";
        String convertID = "2";
        ConversionResp resp = Http.getConversionJSON(result,convertID);
        assertThat(resp.getQuote().getLast_updated(),equalTo("2022-05-13T03:14:00.000Z"));
    }

    @Test
    public void getConversionJSONSucess_Last_updated_mock() {
        String result = "{\"status\":{\"timestamp\":\"2022-05-13T03:14:59.888Z\",\"error_code\":0,\"error_message\":null,\"elapsed\":33,\"credit_count\":1,\"notice\":null},\"data\":{\"id\":1,\"symbol\":\"BTC\",\"name\":\"Bitcoin\",\"amount\":1,\"last_updated\":\"2022-05-13T03:14:00.000Z\",\"quote\":{\"2\":{\"price\":447.4548698400249,\"last_updated\":\"2022-05-13T03:14:00.000Z\"}}}}";
        String convertID = "2";
        ConversionResp resp = Http.getConversionJSON(result,convertID);
        assertThat(resp.getQuote().getLast_updated(),equalTo("2022-05-13T03:14:00.000Z"));
    }



}

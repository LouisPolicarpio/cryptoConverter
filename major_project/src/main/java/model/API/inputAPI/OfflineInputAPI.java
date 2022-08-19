package model.API.inputAPI;

import model.Status;
import model.conversion.ConversionResp;
import model.conversion.Quote;
import model.cryptoInfo.CryptoInfoResp;
import model.cryptoInfo.Info;
import model.cryptoInfo.Urls;
import model.cryptoMap.CryptoMap;
import model.cryptoMap.CryptoMapResp;

public class OfflineInputAPI implements InputAPI {
    @Override
    public ConversionResp getConversion(float amount, String id, String convertID) {
        Status satuts = new Status( "timestamp", 0, "success", 0,2, "notice");
        Quote quote = new Quote(1,"1pm");
        return new ConversionResp(satuts,quote);
    }

    @Override
    public CryptoMapResp getActiveCrypto() {
        Status satuts = new Status( "timestamp", 0, "success", 0,2, "notice");
        CryptoMap cryptoMap1 = new CryptoMap("1", 0 , "testCoin", "test", 1,"first_historical_data", "last_historical_data");
        CryptoMap cryptoMap2 = new CryptoMap("2", 0 , "testCoin", "test", 1,"first_historical_data", "last_historical_data");
        CryptoMap cryptoMap3 = new CryptoMap("3", 0 , "testCoin", "test", 1,"first_historical_data", "last_historical_data");
        CryptoMap[] cryptoMapArr= new CryptoMap[3];
        cryptoMapArr[0] = cryptoMap1;
        cryptoMapArr[1] = cryptoMap2;
        cryptoMapArr[2] = cryptoMap3;
        return new CryptoMapResp(cryptoMapArr,satuts);
    }

    @Override
    public CryptoInfoResp getCryptoInfo(String cryptoID, boolean doCache) throws Exception {
        Status status = new Status( "timestamp", 0, "success", 0,2, "notice");
        Urls urls = new Urls("https://www.google.com/");
        Info info = new Info(urls, "https://picsum.photos/200", cryptoID, "name", "symbol", "desc", "launch");
        return new  CryptoInfoResp(info,status);
    }
}

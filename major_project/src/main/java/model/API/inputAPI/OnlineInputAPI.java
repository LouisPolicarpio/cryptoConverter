package model.API.inputAPI;

import model.API.Http;
import model.DB.Database;
import model.Status;
import model.conversion.ConversionResp;
import model.cryptoInfo.CryptoInfoResp;
import model.cryptoInfo.Info;
import model.cryptoMap.CryptoMapResp;

public class OnlineInputAPI implements InputAPI{

    @Override
    public ConversionResp getConversion(float amount, String id, String convertID) {
        return Http.getConversion(amount, id, convertID);
    }

    @Override
    public CryptoMapResp getActiveCrypto() {
        return Http.getActiveCrypto();
    }

    @Override
    public CryptoInfoResp getCryptoInfo(String cryptoID, boolean doCache) throws Exception {
        CryptoInfoResp post;
        if (Database.doesCurExistInCache(Integer.parseInt(cryptoID))&& doCache){
            Info info =  Database.getInfoCache((int)Float.parseFloat(cryptoID));
            Status status = new Status( "timestamp", 0, "success", 0,0, "notice");
            post = new CryptoInfoResp(info,status);
        }else {
            post = Http.getCryptoInfo(cryptoID);
            Info data = post.getData();
            if(!Database.doesCurExistInCache(Integer.parseInt(cryptoID))){
                Database.addInfoCache((int)Float.parseFloat(data.getId()),data.getLogo(),data.getName(),data.getSymbol(),data.getDescription(),data.getDate_launched(),data.getUrls().getWebsite()[0]);
            }
        }
        return post;
    }
}

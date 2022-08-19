package model.API.inputAPI;

import model.conversion.ConversionResp;
import model.cryptoInfo.CryptoInfoResp;
import model.cryptoMap.CryptoMapResp;

public interface InputAPI {
    public ConversionResp getConversion(float amount, String id, String convertID);
    public CryptoMapResp getActiveCrypto();
    public CryptoInfoResp getCryptoInfo(String cryptoID, boolean doCache) throws Exception;
}

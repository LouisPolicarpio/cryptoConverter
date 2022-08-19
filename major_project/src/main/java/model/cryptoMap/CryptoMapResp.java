package model.cryptoMap;

import model.Status;

public class CryptoMapResp {
    private CryptoMap data[];
    private Status status;

    public CryptoMapResp(CryptoMap[] data, Status status) {
        this.data = data;
        this.status = status;
    }

    public CryptoMap[] getData() {
        return data;
    }

    public Status getStatus() {
        return status;
    }

}

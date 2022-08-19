package model.cryptoInfo;

import model.Status;

public class CryptoInfoResp {
    private Info data;
    private Status status;

    public CryptoInfoResp(Info data, Status status) {
        this.data = data;
        this.status = status;
    }


    public Info getData() {
        return data;
    }


    public Status getStatus() {
        return status;
    }

}

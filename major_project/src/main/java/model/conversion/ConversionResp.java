package model.conversion;

import model.Status;

public class ConversionResp
{
    Status status;
    Quote  quote;

    public ConversionResp(Status status, Quote quote) {
        this.status = status;
        this.quote = quote;
    }


    public Status getStatus() {
        return status;
    }


    public Quote getQuote() {
        return quote;
    }



}

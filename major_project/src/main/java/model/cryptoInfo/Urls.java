package model.cryptoInfo;

public class Urls
{
    private String[] website;


    public Urls(String website) {
        this.website = new String[1];
        this.website[0] = website;
    }

    public String[] getWebsite() {
        return website;
    }
}

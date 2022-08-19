package model.cryptoInfo;

public class Info {
    private Urls urls;
    private String logo;
    private String id;
    private String name;
    private String symbol;
    private String description;
    private String date_launched;
    //tage





    public Info(Urls urls, String logo, String id, String name, String symbol, String description, String date_launched) {
        this.urls = urls;
        this.logo = logo;
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.description = description;
        this.date_launched = date_launched;
    }

    public Urls getUrls() {
        return urls;
    }

    public String getLogo() {
        return logo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    public String getDate_launched() {
        return date_launched;
    }
}

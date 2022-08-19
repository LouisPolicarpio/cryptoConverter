package model.cryptoMap;

public class CryptoMap {
    private String id;
    private int rank;
    private String name;
    private String symbol;
    private int is_active;
    private String first_historical_data;
    private String last_historical_data;

    public CryptoMap(String id, int rank, String name, String symbol, int is_active, String first_historical_data, String last_historical_data) {
        this.id = id;
        this.rank = rank;
        this.name = name;
        this.symbol = symbol;
        this.is_active = is_active;
        this.first_historical_data = first_historical_data;
        this.last_historical_data = last_historical_data;
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
}

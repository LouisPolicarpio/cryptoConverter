package model.conversion;

public class Quote {
    private float price;
    private String last_updated;

    public Quote(float price, String last_updated) {
        this.price = price;
        this.last_updated = last_updated;
    }

    public float getPrice() {
        return price;
    }


    public String getLast_updated() {
        return last_updated;
    }

}

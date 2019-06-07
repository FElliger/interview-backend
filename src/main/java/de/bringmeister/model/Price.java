package de.bringmeister.model;

public class Price {

    private float value;
    private String currency;

    public Price(float value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    Price() {}

    public float getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

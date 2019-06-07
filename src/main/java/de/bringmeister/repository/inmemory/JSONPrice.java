package de.bringmeister.repository.inmemory;

import de.bringmeister.model.Price;

public class JSONPrice {
    private String id;
    private Price price;
    private String unit;

    JSONPrice() {}

    public void setId(String id) {
        this.id = id;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    RawPriceData toRawPriceData() {
        return new RawPriceData(id, price, unit);
    }
}

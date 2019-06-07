package de.bringmeister.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import de.bringmeister.views.Views;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Product {

    private String id;
    private String name;
    private String description;
    private String sku;
    private Map<String, Price> prices;

    public Product(String id, String name, String description, String sku, Map<String, Price> prices) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.prices = prices;
    }

    Product() {}

    @JsonView(Views.Summary.class)
    public String getId() {
        return id;
    }

    @JsonView(Views.Summary.class)
    public String getName() {
        return name;
    }

    @JsonView(Views.Summary.class)
    public String getDescription() {
        return description;
    }

    @JsonView(Views.Summary.class)
    public String getSku() {
        return sku;
    }

    @JsonView(Views.Details.class)
    public Map<String, Price> getPrices() {
        return new HashMap<>(prices);
    }

    @JsonIgnore
    public Price getPrice(String unit) {
        return prices.get(unit);
    }
}

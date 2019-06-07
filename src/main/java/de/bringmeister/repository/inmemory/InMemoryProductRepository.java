package de.bringmeister.repository.inmemory;

import de.bringmeister.model.Product;
import de.bringmeister.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class InMemoryProductRepository implements ProductRepository {

    private Map<String, Product> products;

    private InMemoryProductRepository(Map<String, Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList(products.values());
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    public static InMemoryProductRepository forRawData(DataProvider<RawProductData> productsProvider, DataProvider<RawPriceData> pricesProvider) {
        Map<String, RawProductData> rawProductsBySku = productsProvider.getAllData().collect(toMap(RawProductData::getSku, identity()));

        pricesProvider.getAllData().forEach(rawPrice -> {
            Optional.ofNullable(rawProductsBySku.get(rawPrice.getId())).ifPresent(product -> product.addPrice(rawPrice));
        });

        Map<String, Product> productsById = rawProductsBySku.values().stream().collect(toMap(RawProductData::getId, RawProductData::toProduct));
        return new InMemoryProductRepository(productsById);
    }
}

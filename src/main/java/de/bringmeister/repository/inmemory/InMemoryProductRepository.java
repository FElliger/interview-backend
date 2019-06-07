package de.bringmeister.repository.inmemory;

import de.bringmeister.model.Product;
import de.bringmeister.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return Optional.empty();
    }
}

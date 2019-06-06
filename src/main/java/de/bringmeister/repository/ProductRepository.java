package de.bringmeister.repository;

import de.bringmeister.model.Product;

import java.util.List;
import java.util.Optional;

/**
 * Interface for product repositories
 */
public interface ProductRepository {

    /**
     * @return The list of all products
     */
    List<Product> getAllProducts();

    /**
     * Get a specific product for a given ID
     * @param id the product ID
     * @return An optional containing the product, if it is found. Empty optional otherwise.
     */
    Optional<Product> getProductById(String id);
}

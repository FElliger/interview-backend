package de.bringmeister.controller;

import com.fasterxml.jackson.annotation.JsonView;
import de.bringmeister.controller.exception.NotFoundException;
import de.bringmeister.model.Price;
import de.bringmeister.model.Product;
import de.bringmeister.repository.ProductRepository;
import de.bringmeister.views.Views;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {
    private static final Logger LOG = LoggerFactory.getLogger(ProductsController.class);
    private ProductRepository repository;

    @Autowired
    public ProductsController(ProductRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    @JsonView(Views.Summary.class)
    public List<Product> listProducts() {
        LOG.info("Listing all products...");
        return repository.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{productId}")
    public Product getProductDetails(@PathVariable String productId) {
        LOG.info("Fetching details for product with ID {}", productId);
        return getProductFromRepository(productId);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{productId}/prices/{unit}")
    public Price getPriceForProduct(@PathVariable String productId, @PathVariable String unit) {
        LOG.info("Fetching price for unit {} of product with ID {}", unit, productId);
        Product product = getProductFromRepository(productId);
        return Optional.ofNullable(product.getPrice(unit)).orElseThrow(() -> {
            LOG.error("Price for unit {} was not found for product {}", unit, productId);
            return new NotFoundException();
        });
    }

    private Product getProductFromRepository(final String productId) {
        return repository.getProductById(productId).orElseThrow(() -> {
            LOG.error("Product with ID {} was not found!", productId);
            return new NotFoundException();
        });
    }

}

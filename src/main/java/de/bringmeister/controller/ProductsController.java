package de.bringmeister.controller;

import de.bringmeister.controller.exception.NotFoundException;
import de.bringmeister.model.Price;
import de.bringmeister.model.Product;
import de.bringmeister.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<Product> listProducts() {
        LOG.info("Listing all products...");
        return repository.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{productId}")
    public Product getProductDetails(@PathVariable String productId) {
        LOG.info("Fetching details for product with ID {}", productId);
        return repository.getProductById(productId).orElseThrow(() -> new NotFoundException());
    }

    @RequestMapping(method = RequestMethod.GET, value="/{productId}/prices/{unit}")
    public Price getPriceForProduct() {
        return null;
    }

}

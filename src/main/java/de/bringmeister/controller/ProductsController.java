package de.bringmeister.controller;

import de.bringmeister.model.Price;
import de.bringmeister.model.Product;
import de.bringmeister.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    private ProductRepository repository;

    @Autowired
    public ProductsController(ProductRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> listProducts() {
        return repository.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value="/{productId}")
    public Product getProductDetails() {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value="/{productId}/prices/{unit}")
    public Price getPriceForProduct() {
        return null;
    }

}

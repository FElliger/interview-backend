package de.bringmeister.controller;

import de.bringmeister.model.Price;
import de.bringmeister.model.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Product> listProducts() {
        return null;
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

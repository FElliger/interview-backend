package de.bringmeister.controller;

import de.bringmeister.controller.exception.NotFoundException;
import de.bringmeister.model.Price;
import de.bringmeister.model.Product;
import de.bringmeister.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ProductsControllerTest {

    private static final String BANANA_ID = "product-1";
    private static final String BANANA_UNIT = "piece";
    private static final Price BANANA_PRICE = new Price(0.10f, "EUR");
    private static final Product BANANA = new Product(BANANA_ID, "Banana", "Yellow Thing", "ban", Collections.singletonMap(BANANA_UNIT, BANANA_PRICE));

    private ProductRepository repository;
    private ProductsController controller;

    @Before
    public void setUp() {
        repository = mock(ProductRepository.class);
        controller = new ProductsController(repository);
    }

    @Test
    public void test_list_calls_repository_and_returns_result() {
        //GIVEN
        List<Product> products = Arrays.asList(
                BANANA,
                new Product("product-2", "Apple", "Not a computer", "app", Collections.emptyMap())
        );
        doReturn(products).when(repository).getAllProducts();

        //WHEN
        List<Product> result = controller.listProducts();

        //THEN
        verify(repository).getAllProducts();
        assertThat(result, is(products));
    }

    @Test
    public void test_getProductDetails_returns_product_from_repository() {
        //GIVEN
        doReturn(Optional.of(BANANA)).when(repository).getProductById(BANANA_ID);

        //WHEN
        Product result = controller.getProductDetails(BANANA_ID);

        //THEN
        verify(repository).getProductById(BANANA_ID);
        assertThat(result, is(BANANA));
    }

    @Test(expected = NotFoundException.class)
    public void test_getProductDetails_throws_NotFound_if_product_is_not_found() {
        //GIVEN
        doReturn(Optional.empty()).when(repository).getProductById(BANANA_ID);

        //WHEN/THEN
        controller.getProductDetails(BANANA_ID);
    }

    @Test
    public void test_getPriceForProduct_returns_price_for_given_unit() {
        //GIVEN
        doReturn(Optional.of(BANANA)).when(repository).getProductById(BANANA_ID);

        //WHEN
        Price result = controller.getPriceForProduct(BANANA_ID, BANANA_UNIT);

        //THEN
        assertThat(result, is(BANANA_PRICE));
    }

    @Test(expected = NotFoundException.class)
    public void test_getPriceForProduct_throws_NotFound_if_product_is_not_found() {
        //GIVEN
        doReturn(Optional.empty()).when(repository).getProductById(BANANA_ID);

        //WHEN/THEN
        controller.getPriceForProduct(BANANA_ID, BANANA_UNIT);
    }

    @Test(expected = NotFoundException.class)
    public void test_getPriceForProduct_throws_NotFound_if_price_for_unit_is_not_found() {
        //GIVEN
        doReturn(Optional.of(BANANA)).when(repository).getProductById(BANANA_ID);

        //WHEN/THEN
        controller.getPriceForProduct(BANANA_ID, "invalid-unit");
    }

}

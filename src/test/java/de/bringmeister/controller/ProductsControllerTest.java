package de.bringmeister.controller;

import de.bringmeister.model.Product;
import de.bringmeister.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class ProductsControllerTest {

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
                new Product("product-1", "Banana", "Yellow Thing", "ban", Collections.emptyMap()),
                new Product("product-2", "Apple", "Not a computer", "app", Collections.emptyMap())
        );
        doReturn(products).when(repository).getAllProducts();

        //WHEN
        List<Product> result = controller.listProducts();

        //THEN
        verify(repository).getAllProducts();
        assertThat(result, is(products));
    }

}

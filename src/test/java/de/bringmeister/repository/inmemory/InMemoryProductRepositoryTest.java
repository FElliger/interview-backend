package de.bringmeister.repository.inmemory;

import de.bringmeister.model.Price;
import de.bringmeister.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class InMemoryProductRepositoryTest {

    private static final String ID1 = "product-1";
    private static final String ID2 = "product-2";

    private static final String SKU1 = "sku-1";
    private static final String SKU2 = "sku-2";

    private static final Price BANANA_PIECE_PRICE = new Price(0.1f, "EUR");
    private static final Price BANANA_PACKAGE_PRICE = new Price(1.2f, "EUR");
    private static final Price APPLE_PIECE_PRICE = new Price(0.5f, "EUR");

    private static final List<RawProductData> RAW_PRODUCTS = Arrays.asList(
            new RawProductData(ID1, "Banana", "Yellow thing", SKU1),
            new RawProductData(ID2, "Apple", "Round thing", SKU2)
    );

    private static final List<RawPriceData> RAW_PRICES = Arrays.asList(
            new RawPriceData(SKU1, BANANA_PIECE_PRICE, "piece"),
            new RawPriceData(SKU1, BANANA_PACKAGE_PRICE, "package"),
            new RawPriceData(SKU2, APPLE_PIECE_PRICE, "piece"),
            new RawPriceData("other-sku", new Price(0.6f, "EUR"), "package")
    );

    private InMemoryProductRepository repository;

    @Before
    public void setUp() {
        DataProvider<RawProductData> productsProvider = mock(DataProvider.class);
        DataProvider<RawPriceData> pricesProvider = mock(DataProvider.class);

        doReturn(RAW_PRODUCTS.stream()).when(productsProvider).getAllData();
        doReturn(RAW_PRICES.stream()).when(pricesProvider).getAllData();

        repository = InMemoryProductRepository.forRawData(productsProvider, pricesProvider);
    }

    @Test
    public void test_providesAllProductsFromProvider() {
        //WHEN
        List<Product> products = repository.getAllProducts();

        //THEN
        assertThat(products.size(), is(RAW_PRODUCTS.size()));
        assertThat(products.stream().map(Product::getId).collect(toSet()), is(new HashSet<>(Arrays.asList(ID1, ID2))));
    }

    @Test
    public void test_combines_raw_data_based_on_sku() {
        //WHEN
        Product product1 = repository.getProductById(ID1).get();
        Product product2 = repository.getProductById(ID2).get();

        //THEN
        assertThat(product1.getPrices().size(), is(2));
        assertThat(product1.getPrices().get("piece"), is(BANANA_PIECE_PRICE));
        assertThat(product1.getPrices().get("package"), is(BANANA_PACKAGE_PRICE));

        assertThat(product2.getPrices().size(), is(1));
        assertThat(product2.getPrices().get("piece"), is(APPLE_PIECE_PRICE));
    }
}

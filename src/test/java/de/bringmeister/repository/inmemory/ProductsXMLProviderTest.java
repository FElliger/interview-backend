package de.bringmeister.repository.inmemory;

import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ProductsXMLProviderTest {

    private ProductsXMLProvider provider;

    @Before
    public void setUp() throws Exception {
        XMLInputFactory inputFactory = XMLInputFactory.newFactory();

        XMLStreamReader reader = inputFactory.createXMLStreamReader(getClass().getResourceAsStream("/products.xml"));
        provider = new ProductsXMLProvider(reader);
    }

    @Test
    public void test_getAllData_provides_all_raw_products_from_xml() {
        //WHEN
        List<RawProductData> products = provider.getAllData().collect(Collectors.toList());

        //THEN
        assertThat(products.size(), is(2));
    }
}

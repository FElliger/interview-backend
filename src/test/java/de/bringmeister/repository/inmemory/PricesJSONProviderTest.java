package de.bringmeister.repository.inmemory;

import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PricesJSONProviderTest {

    private PricesJSONProvider provider;

    @Before
    public void setUp() throws Exception {
        InputStream input = getClass().getResourceAsStream("/prices.json");
        provider = new PricesJSONProvider(input);
    }

    @Test
    public void test_getAllData_provides_all_raw_products_from_xml() {
        //WHEN
        List<RawPriceData> products = provider.getAllData().collect(Collectors.toList());

        //THEN
        assertThat(products.size(), is(6));
    }
}

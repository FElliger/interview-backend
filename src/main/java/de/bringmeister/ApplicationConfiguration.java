package de.bringmeister;

import de.bringmeister.repository.inmemory.*;
import de.bringmeister.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ProductRepository productRepository(DataProvider<RawProductData> productsProvider, DataProvider<RawPriceData> pricesProvider) {
        return InMemoryProductRepository.forRawData(productsProvider, pricesProvider);
    }

    @Bean
    public DataProvider<RawProductData> productDataProvider() {
        try {
            XMLInputFactory factory = XMLInputFactory.newFactory();
            XMLStreamReader xmlStram = factory.createXMLStreamReader(getClass().getResourceAsStream("/products/products.xml"));
            return new ProductsXMLProvider(xmlStram);
        } catch(XMLStreamException ex) {
            throw new RuntimeException("Initializing XML products provider failed!", ex);
        }
    }

    @Bean
    public DataProvider<RawPriceData> priceDataProvider() {
        return new PricesJSONProvider(getClass().getResourceAsStream("/products/prices.json"));
    }

}

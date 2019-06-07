package de.bringmeister;

import de.bringmeister.repository.inmemory.InMemoryProductRepository;
import de.bringmeister.repository.ProductRepository;
import de.bringmeister.repository.inmemory.DataProvider;
import de.bringmeister.repository.inmemory.RawPriceData;
import de.bringmeister.repository.inmemory.RawProductData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ProductRepository productRepository(DataProvider<RawProductData> productsProvider, DataProvider<RawPriceData> pricesProvider) {
        return InMemoryProductRepository.forRawData(productsProvider, pricesProvider);
    }

}

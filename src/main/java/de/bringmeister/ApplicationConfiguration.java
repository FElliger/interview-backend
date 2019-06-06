package de.bringmeister;

import de.bringmeister.repository.InMemoryProductRepository;
import de.bringmeister.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ProductRepository productRepository() {
        return new InMemoryProductRepository();
    }

}

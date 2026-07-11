package com.harrison.springboot.di.app.springbootdi.config;

import com.harrison.springboot.di.app.springbootdi.repositories.ProductRepository;
import com.harrison.springboot.di.app.springbootdi.repositories.ProductRepositoryJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:json/products.json")
    private Resource resource;

    @Bean
    ProductRepository productRepositoryJson() {
        return new ProductRepositoryJson(this.resource);
    }
}

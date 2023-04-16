package com.bymdev.grocerystore.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.bymdev.grocerystore.repository")
public class JpaConfiguration {
}

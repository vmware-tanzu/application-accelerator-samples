package com.vmware.acme.catalog;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest(excludeFilters = @ComponentScan.Filter(MetricsConfiguration.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTests {

    @Container
    private static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:14.4-alpine3.16");

    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    static void sqlserverProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Test
    void findAll() {
        var products = this.productRepository.findAll();
        assertThat(products).hasSize(8);
    }

    @Test
    void findById() {
        var product = this.productRepository.findById("533445d-530e-4a76-9398-5d16713b827b");
        assertThat(product).hasValueSatisfying(p -> {
            assertThat(p.getDescription()).isEqualTo("Magic Yoga Mat!");
            assertThat(p.getImageUrl1()).isEqualTo("/static/images/yogamat_square.jpg");
            assertThat(p.getImageUrl2()).isEqualTo("/static/images/yogamat_thumb2.jpg");
            assertThat(p.getImageUrl3()).isEqualTo("/static/images/yogamat_thumb3.jpg");
        });
    }

}

package gift.config;

import gift.controller.AdminController;
import gift.repository.JdbcProductRepository;
import gift.repository.MemoryProductRepository;
import gift.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public AdminController adminController() {
        return new AdminController(productRepository());
    }

    @Bean
    public ProductRepository productRepository() {
//        return new MemoryProductRepository();
        return new JdbcProductRepository(dataSource);
    }
}

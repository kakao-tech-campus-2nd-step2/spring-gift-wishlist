package gift.config;

import gift.repository.ProductJDBCRepository;
import gift.repository.ProductOptionJDBCRepository;
import gift.repository.ProductOptionRepository;
import gift.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ProductConfig {

    @Bean
    public ProductRepository productRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        return new ProductJDBCRepository(jdbcTemplate, dataSource);
    }

    @Bean
    public ProductOptionRepository productOptionRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        return new ProductOptionJDBCRepository(jdbcTemplate, dataSource);
    }
}

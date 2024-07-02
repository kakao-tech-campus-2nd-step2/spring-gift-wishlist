package gift.config;

import gift.model.dao.JDBCTemplateProductDao;
import gift.model.repository.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RepositoryConfig {
    @Bean
    public ProductRepository productRepository(JdbcTemplate jdbcTemplate) {
        return new JDBCTemplateProductDao(jdbcTemplate);
    }
}

package gift.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class DatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initialize() {
        String sql = "CREATE TABLE IF NOT EXISTS kakaoProduct (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "price DECIMAL(10, 2) NOT NULL, " +
                "image_url VARCHAR(255))";
        jdbcTemplate.execute(sql);

        String sql2 = "CREATE TABLE IF NOT EXISTS members (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "email VARCHAR(255) NOT NULL UNIQUE, " +
                "password VARCHAR(255) NOT NULL)";
        jdbcTemplate.execute(sql2);

        String sql3 = "CREATE TABLE IF NOT EXISTS wishes (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "member_id BIGINT NOT NULL, " +
                "product_id BIGINT NOT NULL)";
        jdbcTemplate.execute(sql3);
    }
}

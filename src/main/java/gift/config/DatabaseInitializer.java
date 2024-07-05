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
        System.out.println("Executing SQL: " + sql);
        jdbcTemplate.execute(sql);
        System.out.println("Table kakaoProduct created successfully");

        String sql2 = "CREATE TABLE IF NOT EXISTS members (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "email VARCHAR(255) NOT NULL, " +
                "password VARCHAR(255) NOT NULL)";
        System.out.println("Executing SQL: " + sql2);
        jdbcTemplate.execute(sql2);
        System.out.println("Table members created successfully");
    }
}

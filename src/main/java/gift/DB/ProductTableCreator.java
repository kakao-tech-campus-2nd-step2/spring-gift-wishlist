package gift.DB;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductTableCreator {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductTableCreator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        createProductTable();
    }

    public void createProductTable() {
        String sql = "CREATE TABLE IF NOT EXISTS product (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "name VARCHAR(255) NOT NULL," +
                     "price DECIMAL(10, 2) NOT NULL," +
                     "image_url VARCHAR(255)" +
                     ")";

        jdbcTemplate.execute(sql);
    }
}

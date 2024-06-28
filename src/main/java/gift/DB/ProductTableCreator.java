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
        insertInitialData();
    }

    public void createProductTable() {
        String dropSql = "DROP TABLE IF EXISTS product";

        String sql = "CREATE TABLE IF NOT EXISTS product (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "name VARCHAR(255) NOT NULL," +
                     "price DECIMAL(10, 2) NOT NULL," +
                     "image_url VARCHAR(255)" +
                     ")";

        jdbcTemplate.execute(dropSql);
        jdbcTemplate.execute(sql);
    }

    public void insertInitialData() {
        String insert1 = "INSERT INTO product (name, price, image_url) VALUES ('아이스 아메리카노 T', 4500, 'https://example.com/image.jpg')";
        String insert2 = "INSERT INTO product (name, price, image_url) VALUES ('아이스 카푸치노 M', 4700, 'https://example.com/image.jpg')";
        String insert3 = "INSERT INTO product (name, price, image_url) VALUES ('핫 말차라떼 L', 6800, 'https://example.com/image.jpg')";

        jdbcTemplate.execute(insert1);
        jdbcTemplate.execute(insert2);
        jdbcTemplate.execute(insert3);
    }
}

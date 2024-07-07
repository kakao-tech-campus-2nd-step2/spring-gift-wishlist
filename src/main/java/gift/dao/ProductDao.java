package gift.dao;


import gift.config.DatabaseProperties;
import gift.entity.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;
    private final DatabaseProperties databaseProperties;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate, DatabaseProperties databaseProperties) {
        this.jdbcTemplate = jdbcTemplate;
        this.databaseProperties = databaseProperties;
    }

    @Override
    public void run(String... args) throws Exception {
        var sqlDropTable = "DROP TABLE IF EXISTS product";

        var sqlCreateTable = """
                CREATE TABLE product (
                  id INT PRIMARY KEY AUTO_INCREMENT,
                  name VARCHAR(255) NOT NULL,
                  price INT NOT NULL,
                  imageUrl VARCHAR(4096) NOT NULL
                );
                """;

        jdbcTemplate.execute(sqlDropTable);
        jdbcTemplate.execute(sqlCreateTable);
    }

    public void insertProduct(Product product) {
        var sql = "INSERT INTO product (name, price, imageUrl) values (?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
    }

    public List<Product> selectProduct() {
        var sql = "SELECT * FROM product";
        List<Product> products = jdbcTemplate.query(sql, (rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
        ));
        return products;
    }

    public Product selectOneProduct(Integer id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        RowMapper<Product> rowMapper = (rs, rowNum) -> new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public Integer updateProduct(Product product) {
        var sql = "UPDATE product SET name = ?,price = ?, imageUrl = ? WHERE id = ?";
        return jdbcTemplate.update(sql, product.getName(), product.getPrice(),
                product.getImageUrl(), product.getId());
    }

    public Integer deleteProduct(Integer id) {
        var sql = "DELETE FROM product WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Integer countProduct() {
        var sql = "SELECT COUNT(id) FROM product";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }


}

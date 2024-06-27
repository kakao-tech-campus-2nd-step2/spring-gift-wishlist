package gift.repository;

import gift.domain.Product;
import gift.exception.ErrorCode;
import gift.exception.NotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductJDBCRepository implements ProductRepository{
    private final JdbcTemplate jdbcTemplate;

    public ProductJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    private void createProductTable() {
        jdbcTemplate.execute("CREATE TABLE product (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "description VARCHAR(1024)," +
                "price INT," +
                "url VARCHAR(1024)" +
                ")");
    }

    @Override
    public Product getProductById(Long id) {
        Product product = jdbcTemplate.queryForObject(
                "SELECT * FROM product WHERE id = ?",
                (rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("url")
                ),
                id
        );
        if (product == null) {
            throw new NotFoundException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        return product;
    }

    @Override
    public Long saveProduct(Product product) {
        var sql = "INSERT INTO product (name, description, price, url) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(
                con -> {
                    var ps = con.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, product.getName());
                    ps.setString(2, product.getDescription());
                    ps.setInt(3, product.getPrice());
                    ps.setString(4, product.getUrl());
                    return ps;
                },
                keyHolder
        );

        return keyHolder.getKey().longValue();
    }


    @Override
    public Long updateProduct(Long id, Product product) {
        var sql = "UPDATE product SET name = ?, description = ?, price = ?, url = ? WHERE id = ?";

        jdbcTemplate.update(sql,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getUrl(),
                id);

        return id;
    }

    @Override
    public void deleteProductById(Long id) {
        var sql = "DELETE FROM product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(
                "SELECT * FROM product",
                (rs, rowNum) -> new Product(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getString("url")
                )
        );
    }
}

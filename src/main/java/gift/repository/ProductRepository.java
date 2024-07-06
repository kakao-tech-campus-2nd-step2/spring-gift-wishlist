package gift.repository;

import gift.dto.ProductDTO;
import gift.model.Product;
import gift.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    private static final String productInsertSql = "INSERT INTO PRODUCTS (id, name, price, imageurl) VALUES (?, ?, ?, ?)";
    private static final String allProductsSelectSql = "SELECT * FROM PRODUCTS";
    private static final String productUpdateSql = "UPDATE PRODUCTS SET name = ?, price = ?, imageurl = ? WHERE id = ?";
    private static final String productDeleteSql = "DELETE FROM PRODUCTS WHERE id = ?";

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertToTable(Product product) {
        jdbcTemplate.update(productInsertSql, product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public List<Product> selectAllProducts() {
        return jdbcTemplate.query(
                allProductsSelectSql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("imageurl")
                )
        );
    }

    public void updateToTable(Long id, Product product) {
        jdbcTemplate.update(productUpdateSql, product.getName(), product.getPrice(), product.getImageUrl(), id);
    }

    public void deleteToTable(Long id) {
        jdbcTemplate.update(productDeleteSql, id);
    }
}

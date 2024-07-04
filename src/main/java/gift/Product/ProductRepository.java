package gift.Product;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addProduct(ProductDTO productDto) {
        jdbcTemplate.update(
            "INSERT INTO PRODUCT(NAME, PRICE, IMAGEURL) VALUES(?, ?, ?)",
            productDto.name(), productDto.price(), productDto.imageUrl());
    }

    public List<Product> getAllProducts() {
        return jdbcTemplate.query(
            "SELECT * FROM PRODUCT",
            (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")));
    }

    public void updateProduct(Long id, ProductDTO product) {
        jdbcTemplate.update(
            "UPDATE PRODUCT SET NAME = ?, PRICE = ?, IMAGEURL = ? WHERE ID = ?",
            product.name(), product.price(), product.imageUrl(), id);
    }

    public void deleteProduct(Long id) {
        jdbcTemplate.update("DELETE FROM PRODUCT WHERE ID = ?", id);
    }
}
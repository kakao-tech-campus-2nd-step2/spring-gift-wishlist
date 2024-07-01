package gift.domain.repository;

import org.springframework.stereotype.Repository;
import gift.domain.model.Product;
import gift.domain.model.ProductDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

@Repository
public class ProductJdbcRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Product> productRowMapper = (rs, rowNum) -> new Product(
        rs.getString("name"),
        rs.getLong("price"),
        rs.getString("imageurl")
    );

    public ProductJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isExistProductId(Long id) {
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM products WHERE id = ?",
            Integer.class,
            id
        );
        return count != null && count > 0;
    }

    public Product getProductById(Long id) {
        return jdbcTemplate.queryForObject(
            "SELECT * FROM products WHERE id = ?",
            productRowMapper,
            id
        );
    }

    public boolean isEmptyProductList() {
        Integer count = jdbcTemplate.queryForObject(
            "SELECT COUNT(*) FROM products",
            Integer.class
        );
        return count != null && count == 0;
    }

    public List<ProductDto> getAllProduct() {
        return jdbcTemplate.query(
            "SELECT * FROM products",
            (rs, rowNum) -> new ProductDto(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("imageurl")
            )
        );
    }

    public void addProduct(ProductDto productDto) {
        jdbcTemplate.update(
            "INSERT INTO products (name, price, imageurl) VALUES (?, ?, ?)",
            productDto.getName(),
            productDto.getPrice(),
            productDto.getImageUrl()
        );
    }

    public void updateProduct(ProductDto productDto) {
        jdbcTemplate.update(
            "UPDATE products SET name = ?, price = ?, imageurl = ? WHERE id = ?",
            productDto.getName(),
            productDto.getPrice(),
            productDto.getImageUrl(),
            productDto.getId()
        );
    }

    public void deleteProduct(Long id) {
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
    }
}

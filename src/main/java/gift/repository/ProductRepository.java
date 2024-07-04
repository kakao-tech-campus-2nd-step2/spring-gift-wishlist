package gift.repository;

import gift.DTO.ProductDTO;
import gift.domain.Product;
import gift.domain.Product.CreateProduct;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<ProductDTO> getList() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductDTO.class));
    }

    public ProductDTO getProduct(Long id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(ProductDTO.class), id);
    }

    public void setProduct(CreateProduct create) {
        String sql = "INSERT INTO Product (name, price, imageUrl) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, create.getName(), create.getPrice(), create.getImageUrl());
    }

    public void updateProduct(Long id, Product.UpdateProduct update) {
        String sql = "UPDATE Product SET id = ?, name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, id, update.getName(), update.getPrice(), update.getImageUrl(), id);
    }

    public void removeProduct(Long id) {
        String sql = "DELETE FROM Product WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public boolean validateId(Long id) {
        String sql = "SELECT EXISTS(SELECT 1 FROM Product WHERE id = ?)";
        if (jdbcTemplate.queryForObject(sql, new Object[]{id}, Integer.class)==1){
            return true;
        }
        return false;
    }
}

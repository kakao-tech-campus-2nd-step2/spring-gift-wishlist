package gift.service;

import gift.model.Product;
import gift.model.ProductDTO;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ProductOperation {

    private final JdbcTemplate jdbcTemplate;

    public ProductOperation(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product createProduct(@Valid ProductDTO dto) {
        String sql = "INSERT INTO product (id, name, price, imageUrl) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, dto.getId(), dto.getName(), dto.getPrice(), dto.getImageUrl());
        return getProductById(dto.getId());
    }

    public Product getProductById(long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }

    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM product";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product updateProduct(Long id, @Valid ProductDTO updatedDTO) {
        String sql = "UPDATE product SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, updatedDTO.getName(), updatedDTO.getPrice(),
            updatedDTO.getImageUrl(), id);
        return getProductById(updatedDTO.getId());
    }

    public boolean deleteProduct(Long id) {
        String sql = "DELETE FROM product WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

}
package gift.web;

import gift.web.dto.ProductDto;
import gift.web.exception.ProductNotFoundException;
import java.sql.PreparedStatement;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAO {
    private JdbcTemplate jdbcTemplate;

    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProductDto insertProduct(ProductDto productDto) {
        var sql = "insert into products (name, price, image_url) values (?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection-> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, productDto.name());
            ps.setDouble(2, productDto.price());
            ps.setString(3, productDto.imageUrl());
            return ps;
        }, keyHolder);

        ProductDto newProductDto = new ProductDto(keyHolder.getKey().longValue(), productDto.name(), productDto.price(), productDto.imageUrl());
        return newProductDto;
    }
    private RowMapper<ProductDto> productRowMapper() {
        return (rs, rowNum) -> new ProductDto(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getLong("price"),
            rs.getString("image_url")
        );
    }
    public List<ProductDto> selectAllProducts() {
        var sql = "select * from products";
        return jdbcTemplate.query(sql, productRowMapper());
    }
    public ProductDto selectProductById(long id) {
        var sql = "select * from products where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, productRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new ProductNotFoundException("상품이 존재하지 않습니다.");
        }
    }

    public void updateProduct(ProductDto productDto) {
        var sql = "update products set name = ?, price = ?, image_url = ? where id = ?";
        jdbcTemplate.update(
            sql,
            productDto.name(),
            productDto.price(),
            productDto.imageUrl(),
            productDto.id()
        );
    }

    public void deleteProductById(long id) {
        var sql = "delete from products where id = ?";
        jdbcTemplate.update(sql, id);
    }
}

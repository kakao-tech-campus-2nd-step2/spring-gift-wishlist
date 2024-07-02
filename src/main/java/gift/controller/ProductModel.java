package gift.dao;

import gift.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("products")
                .usingGeneratedKeyColumns("id");
    }

    public void save(Product product) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", product.getName());
        parameters.put("price", product.getPrice());
        parameters.put("imgUrl", product.getImgUrl());

        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        product.setId(newId.longValue());
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        List<Product> query = jdbcTemplate.query(sql, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imgUrl")
        ));
        return query;
    }

    public Product findById(Long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        Product product = jdbcTemplate.queryForObject(sql, new Object[]{id}, (rs, rowNum) -> new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imgUrl")
        ));
        return product;
    }

    public int update(Product product) {
        String sql = "수정할 상품 name = ?, price = ?, imgUrl = ? WHERE id = ?";
        return jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImgUrl(), product.getId());
    }

    public int deleteById(Long id) {
        String sql = "삭제할 상품 WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
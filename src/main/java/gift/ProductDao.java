package gift;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product selectOneProduct(Long id) {
        var sql = "select id, name, price, imageUrl from products where id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                (resultSet, rowNum) -> new Product(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("price"),
                        resultSet.getString("imageUrl")
                ),
                id
        );
    }

    public List<Product> selectAllProducts() {
        String sql = "SELECT id, name, price, imageUrl FROM products";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));
    }

    public void insertProduct(Product product){
        jdbcTemplate.update("INSERT INTO products (id, name, price, imageUrl) VALUES (?, ?, ?, ?)", product.getId(), product.getName(), product.getPrice(), product.getImageUrl());
    }

    public void deleteProduct(Long id){
        jdbcTemplate.update("DELETE FROM products WHERE id = ?", id);
    }

    public void updateProduct(Product product) {
        jdbcTemplate.update("UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?",
                product.getName(), product.getPrice(), product.getImageUrl(), product.getId());
    }

}

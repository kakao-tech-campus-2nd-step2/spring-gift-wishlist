package gift.model;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ProductDaoInMemoryImpl implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public ProductDaoInMemoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Product save(Product product) {
        String sql = "INSERT INTO products(name, price, imageUrl) VALUES (?,?,?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl());
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "SELECT id, name, price, imageUrl FROM products WHERE id = ?";
        Product product = jdbcTemplate.queryForObject(
            sql, new Object[]{id}, (resultSet, rowNum) -> {
                Product p = new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("imageUrl")
                );
                return p;
            });
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT id, name, price, imageUrl FROM products";
        List<Product> products = jdbcTemplate.query(
            sql, (resultSet, rowNum) -> {
                Product product = new Product(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("price"),
                    resultSet.getString("imageUrl")
                );
                return product;
            });
        return products;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Product update(Long id, Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getImageUrl(), id);
        return product;
    }
}

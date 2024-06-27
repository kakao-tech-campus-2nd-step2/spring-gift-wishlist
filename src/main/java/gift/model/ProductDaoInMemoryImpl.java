package gift.model;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductDaoInMemoryImpl implements ProductDao {

    private static final String SQL_INSERT = "INSERT INTO products(name, price, imageUrl) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name, price, imageUrl FROM products WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT id, name, price, imageUrl FROM products";
    private static final String SQL_DELETE_BY_ID = "DELETE FROM products WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE products SET name = ?, price = ?, imageUrl = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Product> rowMapper = new BeanPropertyRowMapper<>(Product.class);

    public ProductDaoInMemoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update(SQL_INSERT, product.getName(), product.getPrice(),
            product.getImageUrl());
    }

    @Override
    public Optional<Product> findById(Long id) {
        try {
            Product product = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID,
                rowMapper, id);
            return Optional.of(product);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL,
            rowMapper);
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update(SQL_DELETE_BY_ID, id);
    }

    @Override
    public void update(Long id, Product product) {
        jdbcTemplate.update(SQL_UPDATE, product.getName(), product.getPrice(),
            product.getImageUrl(), id);
    }
}

package gift.repository;

import gift.model.Product;
import gift.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WishDao {

    private final JdbcTemplate jdbcTemplate;

    public WishDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insert(Wish wish) {
        String sql = "INSERT INTO wishes (productId, userEmail) VALUES (?, ?)";
        jdbcTemplate.update(sql, wish.getProductId(), wish.getUserEmail());
    }

    public List<Product> findAll(String email) {
        String sql = "SELECT p.id, p.name, p.price, p.imageUrl " +
                "FROM products p " +
                "JOIN wishes w ON p.id = w.productId " +
                "WHERE w.userEmail = ?";
        List<Product> products = jdbcTemplate.query(sql, productRowMapper(), email);
        return products;
    }

    private RowMapper<Product> productRowMapper() {
        return (rs, rowNum) -> {
            Product product = new Product(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("imageUrl")
            );
            return product;
        };
    }
}

package gift.repository;

import gift.model.Product;
import gift.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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

}

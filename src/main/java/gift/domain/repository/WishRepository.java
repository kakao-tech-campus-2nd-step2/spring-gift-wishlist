package gift.domain.repository;

import gift.domain.model.Product;
import gift.domain.model.WishResponseDto;
import java.util.Collections;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getProductsByUserEmail(String email) {
        String sql = "SELECT * FROM products WHERE id IN (SELECT product_id FROM wishlists WHERE user_email = ?)";

        return jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) ->
            new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getLong("price"),
                rs.getString("imageUrl")
            )
        );
    }

    public void addWish(String email, Long productId) {
        jdbcTemplate.update(
            "INSERT INTO wishlists (user_email, product_id) VALUES (?, ?)",
            email,
            productId
        );
    }

    public boolean isExistWish(String email, Long productId) {
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(
            "SELECT EXISTS(SELECT 1 FROM wishlists WHERE user_email = ? AND product_id = ?)",
            Boolean.class,
            email,
            productId
        ));
    }

    public void deleteWish(String email, Long productId) {
        jdbcTemplate.update(
            "DELETE FROM wishlists WHERE user_email = ? AND product_id = ?",
            email,
            productId
        );
    }
}

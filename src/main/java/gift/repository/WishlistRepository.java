package gift.repository;

import gift.model.WishlistItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<WishlistItem> wishlistRowMapper;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        wishlistRowMapper = (rs, rowNum) ->
            new WishlistItem(
                rs.getLong("w.id"),
                rs.getLong("w.user_id"),
                rs.getLong("w.product_id"),
                rs.getString("p.name"),
                rs.getLong("w.amount")
            );
    }

    public List<WishlistItem> findListByUserId(long userId) {
        String sql = "SELECT w.id, w.user_id, w.product_id, p.name, w.amount " +
            "FROM wishlist w " +
            "JOIN products p ON w.product_id = p.id " +
            "WHERE w.user_id = ?";
        return jdbcTemplate.query(sql, wishlistRowMapper, userId);
    }

    public void save(WishlistItem wishlistItem) {
        String sql = "INSERT INTO wishlist (user_id, product_id, product_name, amount) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wishlistItem.getUserId(),
                                        wishlistItem.getProductId(),
                                        wishlistItem.getProductName(),
                                        wishlistItem.getAmount());
    }
}

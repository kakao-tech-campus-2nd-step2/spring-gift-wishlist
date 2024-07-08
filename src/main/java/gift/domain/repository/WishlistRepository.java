package gift.domain.repository;

import gift.domain.entity.User;
import gift.domain.dto.WishlistAddResponseDto;
import gift.domain.entity.Wishlist;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private RowMapper<Wishlist> getRowMapper() {
        return (resultSet, rowNum) -> new Wishlist(
            resultSet.getLong("product_id"),
            resultSet.getString("user_email"),
            resultSet.getLong("quantity")
        );
    }

    public List<Wishlist> findWishlistByUser(User user) {
        String sql = "SELECT * FROM wishlist WHERE user_email = ?";
        return jdbcTemplate.query(sql, getRowMapper(), user.email());
    }

    public Optional<Wishlist> findByUserEmailAndProductId(String userEmail, Long productId) {
        try {
            String sql = "SELECT * from wishlist WHERE product_id = ? AND user_email = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, getRowMapper(), productId, userEmail));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void save(Wishlist wishlist) {
        String sqlInsert = "INSERT INTO wishlist (product_id, user_email, quantity) values (?, ?, ?)";
        jdbcTemplate.update(sqlInsert, wishlist.productId(), wishlist.userEmail(), wishlist.quantity());
    }

    public void update(Wishlist wishlist) {
        String sql = "UPDATE wishlist SET quantity = ? WHERE product_id = ? AND user_email = ?";
        jdbcTemplate.update(sql, wishlist.quantity(), wishlist.productId(), wishlist.userEmail());
    }

    public void delete(Wishlist wishlist) {
        String sqlDelete = "DELETE FROM wishlist WHERE product_id = ? AND user_email = ?";
        jdbcTemplate.update(sqlDelete, wishlist.productId(), wishlist.userEmail());
    }
}

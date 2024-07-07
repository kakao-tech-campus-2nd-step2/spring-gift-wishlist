package gift.domain.wishlist;

import gift.domain.user.User;
import gift.domain.wishlist.dto.WishlistAddResponseDto;
import java.util.List;
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

    public WishlistAddResponseDto save(Wishlist wishlist) {
        try {
            String sqlFind = "SELECT * FROM wishlist WHERE product_id = ? AND user_email = ?";
            Wishlist result = jdbcTemplate.queryForObject(sqlFind, getRowMapper(), wishlist.productId(), wishlist.userEmail());

            //수량은 최소한 0 이상이어야 함
            Long resultQuantity = wishlist.quantity() + result.quantity();

            if (resultQuantity <= 0) {
                String sqlDelete = "DELETE FROM wishlist WHERE product_id = ? AND user_email = ?";
                jdbcTemplate.update(sqlDelete, wishlist.productId(), wishlist.userEmail());
                return new WishlistAddResponseDto("delete", 0L);
            }

            String sqlUpdate = "UPDATE wishlist SET quantity = ? WHERE product_id = ? AND user_email = ?";
            jdbcTemplate.update(sqlUpdate, resultQuantity, wishlist.productId(), wishlist.userEmail());
            return new WishlistAddResponseDto("add", resultQuantity);

        } catch (EmptyResultDataAccessException e) {
            String sqlInsert = "INSERT INTO wishlist (product_id, user_email, quantity) values (?, ?, ?)";
            jdbcTemplate.update(sqlInsert, wishlist.productId(), wishlist.userEmail(), wishlist.quantity());
            return new WishlistAddResponseDto("create", wishlist.quantity());
        }
    }

}

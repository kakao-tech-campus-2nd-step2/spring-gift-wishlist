package gift.domain.repository;

import gift.domain.dto.WishlistResponseDto;
import gift.domain.entity.User;
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

    private RowMapper<Wishlist> getWishlistRowMapper() {
        return (resultSet, rowNum) -> new Wishlist(
            resultSet.getLong("product_id"),
            resultSet.getString("user_email"),
            resultSet.getLong("quantity")
        );
    }

    private RowMapper<WishlistResponseDto> getWishlistResponseDtoRowMapper() {
        return (resultSet, rowNum) -> new WishlistResponseDto(
            resultSet.getLong("product_id"),
            resultSet.getString("name"),
            resultSet.getLong("price"),
            resultSet.getString("image_url"),
            resultSet.getLong("quantity")
        );
    }

    public List<WishlistResponseDto> findWishlistByUser(User user) {
        String sql = "SELECT product_id, name, price, image_url, quantity "
            + "FROM wishlist INNER JOIN products ON products.id = wishlist.product_id "
            + "WHERE user_email = ?";
        return jdbcTemplate.query(sql, getWishlistResponseDtoRowMapper(), user.email());
    }

    public Optional<Wishlist> findByUserEmailAndProductId(String userEmail, Long productId) {
        try {
            String sql = "SELECT * from wishlist WHERE product_id = ? AND user_email = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, getWishlistRowMapper(), productId, userEmail));
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

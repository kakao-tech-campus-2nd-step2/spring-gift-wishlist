package gift.wishlist.model;

import gift.product.model.dto.ProductResponse;
import gift.wishlist.model.dto.AddWishRequest;
import gift.wishlist.model.dto.WishListResponse;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WishListResponse> findWishesByUserId(Long userId) {
        String sql = """
                SELECT p.id, p.name, p.price, p.image_url, w.quantity 
                FROM Wish w
                JOIN Product p ON w.productId = p.id
                WHERE w.userId = ?
                """;
        return jdbcTemplate.query(sql, wishListResponseRowMapper());
    }

    private RowMapper<WishListResponse> wishListResponseRowMapper() {
        return (rs, rowNum) -> new WishListResponse(
                rs.getLong("id"),
                new ProductResponse(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("image_url")
                ),
                rs.getInt("quantity")
        );
    }

    public void addWish(AddWishRequest addWishRequest) {
        String sql = "INSERT INTO Wish (userId, productId, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, addWishRequest.getUserId(), addWishRequest.getProductId(),
                addWishRequest.getQuantity());
    }

    public void updateWishQuantity(Long wishId, int quantity) {
        String sql = "UPDATE Wish SET quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql, quantity, wishId);
    }

    public void deleteWish(Long wishId) {
        String sql = "DELETE FROM Wish WHERE id = ?";
        jdbcTemplate.update(sql, wishId);
    }
}

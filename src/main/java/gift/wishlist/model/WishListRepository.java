package gift.wishlist.model;

import gift.product.model.dto.ProductResponse;
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
        return jdbcTemplate.query(sql, new Object[]{userId}, wishListResponseRowMapper());
    }

    private RowMapper<WishListResponse> wishListResponseRowMapper() {
        return (rs, rowNum) -> new WishListResponse(
                new ProductResponse(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getInt("price"),
                        rs.getString("image_url")
                ),
                rs.getInt("quantity")
        );
    }

    
}

package gift.repository;

import gift.dto.wishlist.WishProductDto;
import gift.entity.Wishlist;
import gift.model.Product;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepository {
    private final JdbcTemplate jdbcTemplate;
    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WishProductDto> findByUserId(Long userId) {
        String sql = "SELECT p.id, p.name, p.price, p.imageUrl, w.quantity "
                + "FROM wishlist w "
                + "JOIN products p ON w.product_id = p.id "
                + "WHERE w.user_id = ?";

        return jdbcTemplate.query(sql, wishProductRowMapper, userId);
    }

    public List<WishProductDto> insert(Wishlist wishlist) {
        String sql = "INSERT INTO wishlist (user_id, product_id,quantity) "
            + "VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, wishlist.userId(), wishlist.productId(), wishlist.quantity());

        return findByUserId(wishlist.userId());
    }

    public void update(Wishlist wishlist) {
        String sql = "UPDATE wishlist SET quantity = ? WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, wishlist.quantity(), wishlist.userId(), wishlist.productId());
    }

    public void delete(Wishlist wishlist) {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, wishlist.userId(), wishlist.productId());
    }

    private final RowMapper<WishProductDto> wishProductRowMapper = (rs, rowNum) -> {
        Product product = new Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("imageUrl")
        );
        return new WishProductDto(product, rs.getInt("quantity"));
    };
}

package gift.repository;

import gift.dto.wishlist.WishDto;
import gift.entity.Wish;
import gift.model.Product;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;
    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WishDto> findByUserId(Long userId) {
        String sql = "SELECT p.id, p.name, p.price, p.imageUrl, w.quantity "
                + "FROM wishes w "
                + "JOIN products p ON w.product_id = p.id "
                + "WHERE w.user_id = ?";

        try {
            return jdbcTemplate.query(sql, wishProductRowMapper, userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<WishDto> insert(Wish wish) {
        String sql = "INSERT INTO wishes (user_id, product_id, quantity) "
            + "VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, wish.userId(), wish.productId(), wish.quantity());

        return findByUserId(wish.userId());
    }

    public void update(Wish wish) {
        String sql = "UPDATE wishes SET quantity = ? WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, wish.quantity(), wish.userId(), wish.productId());
    }

    public void delete(Wish wish) {
        String sql = "DELETE FROM wishes WHERE user_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, wish.userId(), wish.productId());
    }

    private final RowMapper<WishDto> wishProductRowMapper = (rs, rowNum) -> {
        Product product = new Product(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getInt("price"),
            rs.getString("imageUrl")
        );
        return new WishDto(product, rs.getInt("quantity"));
    };
}

package gift.feat.wishList;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WishListDao {

	private final JdbcTemplate jdbcTemplate;
	private final RowMapper<WishProduct> wishListRowMapper = (rs, rowNum) -> WishProduct.of(rs.getLong("user_id"), rs.getLong("product_id"));

	public void save(WishProduct wishProduct) {
		String sql = "INSERT INTO wishList (user_id, product_id) VALUES (?, ?)";
		jdbcTemplate.update(sql, wishProduct.getUserId(), wishProduct.getProductId());
	}

	public List<WishProduct> findByUserId(Long userId) {
		String sql = "SELECT * FROM wishList WHERE user_id = ?";
		return jdbcTemplate.query(sql, new Object[]{userId}, wishListRowMapper);
	}

	public void deleteByUserIdAndProductId(Long userId, Long productId) {
		String sql = "DELETE FROM wishList WHERE user_id = ? AND product_id = ?";
		jdbcTemplate.update(sql, userId, productId);
	}
}


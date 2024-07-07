package gift.domain.wishlist;

import gift.domain.user.User;
import gift.domain.wishlist.dto.WishlistAddResponseDto;
import gift.domain.wishlist.dto.WishlistDto;
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

    public WishlistAddResponseDto save(Wishlist wishlist) {
        Optional<Wishlist> result = findByUserEmailAndProductId(wishlist.userEmail(), wishlist.productId());
        if (result.isEmpty()) {
            //아이템이 없으면 새 데이터 삽입
            String sqlInsert = "INSERT INTO wishlist (product_id, user_email, quantity) values (?, ?, ?)";
            jdbcTemplate.update(sqlInsert, wishlist.productId(), wishlist.userEmail(), wishlist.quantity());
            return new WishlistAddResponseDto("create", wishlist.quantity());
        }

        //수량은 최소한 0 이상이어야 함
        Long resultQuantity = wishlist.quantity() + result.get().quantity();

        //업데이트 후 수량이 음수면 delete 수행
        if (resultQuantity <= 0) {
            delete(wishlist);
            return new WishlistAddResponseDto("delete", 0L);
        }

        //아이템이 이미 존재하면 업데이트 수행
        update(new Wishlist(wishlist.productId(), wishlist.userEmail(), resultQuantity));
        return new WishlistAddResponseDto("add", resultQuantity);
    }

    public void update(Wishlist wishlist) {
        String sql = "UPDATE wishlist SET quantity = ? WHERE product_id = ? AND user_email = ?";
        jdbcTemplate.update(sql, getRowMapper(), wishlist.quantity(), wishlist.productId(), wishlist.userEmail());
    }

    public void delete(Wishlist wishlist) {
        String sqlDelete = "DELETE FROM wishlist WHERE product_id = ? AND user_email = ?";
        jdbcTemplate.update(sqlDelete, wishlist.productId(), wishlist.userEmail());
    }
}

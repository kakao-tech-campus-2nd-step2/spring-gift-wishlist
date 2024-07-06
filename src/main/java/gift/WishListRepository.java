package gift;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class WishListRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<WishList> wishListRowMapper = (resultSet, rowNum) ->
        new WishList(
                resultSet.getLong("id"),
                resultSet.getLong("memberId"),
                resultSet.getLong("productId")
            );

    public List<WishList> findByMemberId(Long memberId) {
        return jdbcTemplate.query("SELECT * FROM wish_list WHERE memberId = ?", wishListRowMapper, memberId);
    }

    public void addProductToWishList(Long memberId, Long productId) {
        jdbcTemplate.update("INSERT INTO wish_list (memberId, productId) VALUES (?, ?)", memberId, productId);
    }

    public void removeProductFromWishList(Long memberId, Long productId) {
        jdbcTemplate.update("DELETE FROM wish_list WHERE memberId = ? AND productId = ?", memberId, productId);
    }
}

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

    private final RowMapper<WishList> wishListRowMapper = (resultSet, rowNum) -> {
        WishList wishList = new WishList();
        wishList.setId(resultSet.getLong("id"));
        wishList.setMemberId(resultSet.getLong("memberId"));
        wishList.setProductId(resultSet.getLong("productId"));
        return wishList;
    };

    public List<WishList> findByMemberId(Long memberId) {
        return jdbcTemplate.query("SELECT * FROM wish_list WHERE memberId = ?", wishListRowMapper, memberId);
    }
}

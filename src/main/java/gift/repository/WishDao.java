package gift.repository;

import gift.model.Wish;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishDao {

    private final JdbcTemplate jdbcTemplate;

    public WishDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish> selectAllWishesByUserId(Long userId) {
        return jdbcTemplate.query(
            WishQuery.SELECT_ALL_WISHES_BY_USERID.getQuery(),
            (resultSet, rowNum) -> new Wish(
                resultSet.getLong("user_id"),
                resultSet.getString("product_name"),
                resultSet.getInt("count")
            ),
            userId
        );
    }

    public void insertWish(Wish wish) {
        jdbcTemplate.update(WishQuery.INSERT_WISH_BY_USERID.getQuery(), wish.getProductName(),
            wish.getCount(), wish.getUserId());
    }

    public void updateWish(Wish wish) {
        jdbcTemplate.update(WishQuery.UPDATE_WISH_BY_USERID_PRODUCTNAME.getQuery(), wish.getCount(),
            wish.getUserId(), wish.getProductName());
    }

    public void deleteWish(Wish wish) {
        jdbcTemplate.update(WishQuery.DELETE_WISH_BY_USERID_PRODUCTNAME.getQuery(),
            wish.getUserId(), wish.getProductName());
    }
}

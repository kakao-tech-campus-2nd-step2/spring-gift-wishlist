package gift.domain.wish;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepository {
    private JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    private RowMapper<Wish> wishRowMapper() {
        return (rs, rowNum) -> new Wish(
            rs.getLong("id"),
            rs.getString("email"),
            rs.getLong("productId"),
            rs.getLong("count")
        );
    }
    public List<Wish> selectAllWishes(String email) {
        var sql = "select * from wishes where email = ?";
        return jdbcTemplate.query(sql, new Object[]{email}, wishRowMapper());
    }

    public Wish insertWish(Wish wish, String email) {
        var sql = "insert into wishes(email, productId, count) values(?, ?, ?)";
        jdbcTemplate.update(sql, email, wish.productId(), wish.count());
        return getWishBySignature(email, wish.productId());
    }

    public Wish getWishBySignature(String email, long productId) {
        var sql = "select * from wishes where email = ? and productId = ?";
        return jdbcTemplate.queryForObject(sql, wishRowMapper(), email, productId);
    }
}

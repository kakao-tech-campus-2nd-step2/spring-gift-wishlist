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
}

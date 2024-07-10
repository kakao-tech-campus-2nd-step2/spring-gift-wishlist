package gift.repository;

import gift.dto.WishInfoDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishDAO {
    private final JdbcTemplate jdbcTemplate;

    public WishDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<WishInfoDTO> findWishes(long userId) {
        String sql = "SELECT * FROM wish WHERE user_id = ?";

        return jdbcTemplate.query(sql, (wishRecord, rowNum) -> new WishInfoDTO(
                wishRecord.getLong("id"),
                wishRecord.getLong("user_id"),
                wishRecord.getLong("product_id"),
                wishRecord.getInt("quantity")
        ), userId);
    }
}
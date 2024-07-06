package gift.domain.wish;

import gift.dto.WishAddRequestDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class WishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addWish(Long memberId, WishAddRequestDto request) {
        if (isAlreadyExist(memberId, request.getProductId())) {
            String sql = "SELECT quantity FROM wish WHERE member_id = ? AND product"
            String sql = "UPDATE wish SET "
        }
        String sql = "INSERT INTO wish(member_id,product_id,quantity) VALUES(?,?,?)";
        jdbcTemplate.update(sql, memberId, request.getProductId(), request.getQuantity());
    }

    public ArrayList<Wish> getAllWishes(Long memberId) {
        String sql = "SELECT * FROM wish WHERE member_id = ?";
        return (ArrayList<Wish>) jdbcTemplate.query(sql, (rs, rowNow) -> {
            Long id = rs.getLong("id");
            Long productId = rs.getLong("product_id");
            int quantity = rs.getInt("quantity");
            return new Wish(id, memberId, productId, quantity);
        }, memberId);
    }

    public void deleteWish(Long memberId, Long productId) {
        String sql = "DELETE FROM wish WHERE member_id = ? AND product_id = ?";
        jdbcTemplate.update(sql,memberId,productId);
    }

    private boolean isAlreadyExist(Long memberId,Long productId) {
        String sql = "SELECT FROM wish WHERE member_id = ? AND product_id = ?";
        return !jdbcTemplate.query(sql, (rs, rowNum) -> 0).isEmpty();
    }
}

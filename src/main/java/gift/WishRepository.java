package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class WishRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Wish> findByMemberId(Long memberId) {
        String sql = "SELECT * FROM wishes WHERE member_id = ?";
        return jdbcTemplate.query(sql, new Object[]{memberId}, new WishRowMapper());
    }

    public void save(Wish wish) {
        String sql = "INSERT INTO wishes (member_id, product_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, wish.getMemberId(), wish.getProductId());
    }

    public void deleteByMemberIdAndProductId(Long memberId, Long productId) {
        String sql = "DELETE FROM wishes WHERE member_id = ? AND product_id = ?";
        jdbcTemplate.update(sql, memberId, productId);
    }

    class WishRowMapper implements RowMapper<Wish> {
        @Override
        public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
            Wish wish = new Wish();
            wish.setId(rs.getLong("id"));
            wish.setMemberId(rs.getLong("member_id"));
            wish.setProductId(rs.getLong("product_id"));
            return wish;
        }
    }
}

package gift.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initialize() {
        createWishListTable();
        addWishProduct(1L, 1L);
        addWishProduct(1L, 2L);
        addWishProduct(1L, 3L);
        addWishProduct(1L, 4L);
    }

    private void createWishListTable() {
        var sql = """
                create table wishList (
                  id bigint auto_increment,
                  memberId bigint,
                  productId bigint,
                  primary key (id)
                )
                """;
        jdbcTemplate.execute(sql);
    }

    public Long addWishProduct(Long memberId, Long productId) {
        String sql = "insert into wishlist (memberId, productId) values(?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, memberId);
            ps.setLong(2, productId);
            return ps;
        }, keyHolder);
        if (rowsAffected > 0) {
            return keyHolder.getKey().longValue();
        }
        return -1L;
    }

    public List<Long> getWishListProductIdsByMemberId(Long memberId) {
        String sql = "select productId from wishList where memberId = ?";
        List<Long> productId = jdbcTemplate.query(sql, new Object[]{memberId}, (rs, rowNum) -> rs.getLong("productId"));
        return productId;
    }

    public Long deleteProduct(Long memberId, Long productId) {
        String sql = "delete from wishList where memberId = ? and productId = ? ";
        jdbcTemplate.update(sql, memberId, productId);
        return productId;
    }

}

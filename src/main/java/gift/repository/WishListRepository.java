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
                  amount int,
                  primary key (id),
                  FOREIGN KEY (memberId) REFERENCES member(id),
                  FOREIGN KEY (productId) REFERENCES product(id)
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

    public List<ProductAmount> getWishListProductIdsByMemberId(Long memberId) {
        String sql = "select productId, amount from wishList where memberId = ?";
        return jdbcTemplate.query(sql, new Object[]{memberId}, new RowMapper<ProductAmount>() {
            public ProductAmount mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long productId = rs.getLong("productId");
                int amount = rs.getInt("amount");
                return new ProductAmount(productId, amount);
            }
        });
    }

    public Long deleteProduct(Long memberId, Long productId) {
        String sql = "delete from wishList where memberId = ? and productId = ? ";
        jdbcTemplate.update(sql, memberId, productId);
        return productId;
    }

}

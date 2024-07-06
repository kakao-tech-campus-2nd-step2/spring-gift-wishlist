package gift.member.persistence.repository;

import gift.global.exception.ErrorCode;
import gift.global.exception.NotFoundException;
import gift.member.persistence.entity.Wishlist;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishlistJDBCRepository implements WishlistRepository{
    private final JdbcTemplate jdbcTemplate;

    public WishlistJDBCRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Wishlist> getWishListByMemberId(Long memberId) {
        var sql = "SELECT * FROM wishlist WHERE member_id = ?";

        try {
            return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Wishlist(
                    rs.getLong("id"),
                    rs.getLong("product_id"),
                    rs.getLong("member_id"),
                    rs.getInt("count")
                ),
                memberId
            );
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(ErrorCode.DB_NOT_FOUND,
                "Wishlist with member id " + memberId + " not found");
        }

    }
}

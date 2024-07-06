package gift.repository;

import gift.model.WishProduct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class WishProductJDBCRepository implements WishProductRepository {

    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsert jdbcInsert;

    public WishProductJDBCRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("wish_product")
                .usingGeneratedKeyColumns("id");
    }

    private final RowMapper<WishProduct> wishProductRowMapper = (rs, rowNum) -> new WishProduct(
            rs.getLong("id"),
            rs.getLong("product_id"),
            rs.getLong("member_id"),
            rs.getInt("count"));

    public WishProduct save(WishProduct wishProduct) {
        var id = insertAndReturnId(wishProduct);
        return createWishProductWithId(id, wishProduct);
    }

    public void update(WishProduct wishProduct) {

    }

    public boolean existsByProductAndMember(Long productId, Long memberId) {
        return false;
    }

    public List<WishProduct> findAll() {
        return null;
    }

    public void deleteById(Long id) {}

    private Long insertAndReturnId(WishProduct wishProduct) {
        var param = new BeanPropertySqlParameterSource(wishProduct);
        return jdbcInsert.executeAndReturnKey(param).longValue();
    }

    private WishProduct createWishProductWithId(Long id, WishProduct wishProduct) {
        return new WishProduct(id, wishProduct.getProductId(), wishProduct.getMemberId(), wishProduct.getCount());
    }
}

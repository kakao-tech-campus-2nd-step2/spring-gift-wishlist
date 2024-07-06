package gift.repository;

import gift.exception.NotFoundElementException;
import gift.model.WishProduct;
import org.springframework.dao.EmptyResultDataAccessException;
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
        var sql = "update wish_product set count = ? where id = ?";
        jdbcTemplate.update(sql, wishProduct.getCount(), wishProduct.getId());
    }

    public boolean existsByProductAndMember(Long productId, Long memberId) {
        return false;
    }

    public WishProduct findById(Long id) {
        var sql = "select id, product_id, member_id, count from wish_product where id = ?";
        try {
            var wishProduct = jdbcTemplate.queryForObject(sql, wishProductRowMapper, id);
            return wishProduct;
        } catch (EmptyResultDataAccessException exception) {
            throw new NotFoundElementException(exception.getMessage());
        }
    }

    public List<WishProduct> findAll() {
        var sql = "select id, product_id, member_id, count from wish_product";
        var wishProducts = jdbcTemplate.query(sql, wishProductRowMapper);
        return wishProducts;
    }

    public void deleteById(Long id) {
    }

    private Long insertAndReturnId(WishProduct wishProduct) {
        var param = new BeanPropertySqlParameterSource(wishProduct);
        return jdbcInsert.executeAndReturnKey(param).longValue();
    }

    private WishProduct createWishProductWithId(Long id, WishProduct wishProduct) {
        return new WishProduct(id, wishProduct.getProductId(), wishProduct.getMemberId(), wishProduct.getCount());
    }
}
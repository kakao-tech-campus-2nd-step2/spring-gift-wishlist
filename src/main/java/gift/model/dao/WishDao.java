package gift.model.dao;

import gift.model.Wish;
import gift.model.repository.WishRepository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class WishDao implements WishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Wish entity) {
        if (entity.isNew()) {
            jdbcTemplate.update(WishQuery.INSERT_WISH, entity.getUserId(), entity.getProductId(), entity.getAmount());
            return;
        }
        update(entity);
    }

    @Override
    public void update(Wish entity) {
        jdbcTemplate.update(WishQuery.UPDATE_WISH, entity.getAmount(), entity.getId());
    }

    @Override
    public Optional<Wish> find(Long id) {
        //NOT_IMPLEMENTED
        return Optional.empty();
    }

    @Override
    public void delete(Wish entity) {
        //NOT_IMPLEMENTED
    }

    @Override
    public List<Wish> findAll() {
        //NOT_IMPLEMENTED
        return null;
    }

    @Override
    public Optional<Wish> findByIdAndUserId(Long id, Long userId) {
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(WishQuery.SELECT_WISH_BY_ID_AND_USER_ID,
                            new Object[]{userId, id}, (rs, rowNum) -> wishMapper(rs)));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public Wish wishMapper(ResultSet rs) throws SQLException {
        return new Wish(
                rs.getLong("id"),
                rs.getLong("user_id"),
                rs.getLong("product_id"),
                rs.getInt("amount")
        );
    }
}

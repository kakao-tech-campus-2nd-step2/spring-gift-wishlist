package gift.model.dao;

import gift.model.Wish;
import gift.model.repository.WishRepository;
import java.util.List;
import java.util.Optional;
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
        jdbcTemplate.update(WishQuery.UPDATE_WISH, entity.getUserId(), entity.getProductId(), entity.getAmount(),
                entity.getUserId(), entity.getProductId());
    }

    @Override
    public Optional<Wish> find(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Wish entity) {

    }

    @Override
    public List<Wish> findAll() {
        return null;
    }
}

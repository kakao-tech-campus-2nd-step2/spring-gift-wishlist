package gift.repository.impls;

import gift.domain.Wish;
import gift.repository.WishRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class WishRepositoryImpl implements WishRepository {
    private final JdbcTemplate jdbcTemplate;

    public WishRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Wish wish) {
        var sql = "INSERT INTO wishes(user_id, product_id, quantity) VALUES (?,?,?)";
        jdbcTemplate.update(sql,wish.getUserId(), wish.getProductId(), wish.getQuantity());

    }

    @Override
    public Optional<List<Wish>> findByUserId(Long userId) {
        var sql = "SELECT * FROM wishes WHERE user_id = ?";
        List<Wish> wishes = jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Wish(
                        resultSet.getLong("id"),
                        resultSet.getLong("userId"),
                        resultSet.getLong("productId"),
                        resultSet.getInt("quantity")
                ),
                userId
        );
        if (wishes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(wishes);
    }

    @Override
    public Optional<Wish> findByIdAndUserId(Long id, Long userId) {
        var sql = "SELECT * FROM wishes WHERE id = ? and userId = ?";
        List<Wish> wishes = jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Wish(
                        resultSet.getLong("id"),
                        resultSet.getLong("userId"),
                        resultSet.getLong("productId"),
                        resultSet.getInt("quantity")
                ),
                id,
                userId
        );
        if (wishes.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(wishes.get(0));
    }

    @Override
    public void delete(Long id) {
        var sql = "DELETE FROM wishes WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateQuantity(Long id, int quantity) {
        var sql = "UPDATE products SET quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql,id,quantity);
    }
}

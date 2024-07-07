package gift.repository;

import gift.domain.Wish;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishRepositoryImpl implements WishRepository{
    private final JdbcTemplate jdbcTemplate;

    public WishRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public void addWish(Wish wish) {
        var sql = "INSERT INTO wishes (user_id,product_id,quantity) VALUES(?,?,?)";
        Object[] params = new Object[]{wish.getUserId(), wish.getProductId(), wish.getQuantity()};
        jdbcTemplate.update(sql, params);
    }

}

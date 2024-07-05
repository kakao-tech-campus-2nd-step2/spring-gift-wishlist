package gift.repository;

import gift.domain.WishListRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository {
    JdbcTemplate jdbcTemplate;

    public WishListRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void create(WishListRequest wishListRequest) {

    }
}

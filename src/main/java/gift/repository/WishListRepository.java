package gift.repository;

import gift.domain.Menu;
import gift.domain.WishListRequest;
import gift.domain.WishListResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class WishListRepository {
    private final SimpleJdbcInsert simpleJdbcInsert;
    JdbcTemplate jdbcTemplate;


    public WishListRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
        this.simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("wishlist")
                .usingGeneratedKeyColumns("id")
        ;
    }

    private final RowMapper<WishListResponse> menuRowMapper = new RowMapper<WishListResponse>() {
        @Override
        public WishListResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new WishListResponse(
                    rs.getString("memberId"),
                    rs.getLong("menuId")
            );
        }
    };

    public void create(WishListRequest wishListRequest) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(wishListRequest);
        simpleJdbcInsert.execute(params);
    }


}

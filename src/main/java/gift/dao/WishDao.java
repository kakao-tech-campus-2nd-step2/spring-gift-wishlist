package gift.dao;

import gift.model.wish.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishDao {
    private final JdbcTemplate jdbcTemplate;
    public WishDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish> getAllWishes(String email) {
        var sql = "select productid, email, productName, amount from wishes where email = ?";
        return jdbcTemplate.query(
                sql,
                (resultSet, rowNum) -> new Wish(
                        resultSet.getLong("productid"),
                        resultSet.getString("email"),
                        resultSet.getString("productName"),
                        resultSet.getInt("amount")
                ),
                email
        );
    }

    public void insertWish(Wish wish) {
        var sql = "insert into wishes (productid, email, productName, amount) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, wish.getProductId(), wish.getMemberEmail(), wish.getProductName(), wish.getAmount());
    }

    public void deleteWish(long id) {
        var sql = "delete from wishes where productid = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateWish(Wish wish){
        var sql = "update products set amount = ? where id = ? ";
        jdbcTemplate.update(sql, wish.getAmount(), wish.getProductId());
    }
}

package gift.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WishRowMapper implements RowMapper<Wish> {
    @Override
    public Wish mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product(
                rs.getLong("product_id"),
                rs.getString("name"),
                rs.getInt("price"),
                rs.getString("imageUrl")
        );
        Wish wish = new Wish(
                rs.getLong("id"),
                rs.getLong("member_id"),
                product,
                rs.getTimestamp("createdAt").toLocalDateTime(),
                rs.getTimestamp("updatedAt").toLocalDateTime()
        );
        return wish;
    }
}

package gift.repository;

import gift.dto.WishedProductDTO;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class WishedProductDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WishedProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Collection<WishedProductDTO> getWishedProducts(String memberEmail) {
        var sql = "SELECT * FROM WISHED_PRODUCT WHERE member_email = ?";
        return jdbcTemplate.query(sql, wishedProductRowMapper(), memberEmail);
    }

    private RowMapper<WishedProductDTO> wishedProductRowMapper() {
        return (resultSet, rowNum) -> new WishedProductDTO(
            resultSet.getString("member_email"),
            resultSet.getLong("product_id"),
            resultSet.getInt("amount"));
    }
}

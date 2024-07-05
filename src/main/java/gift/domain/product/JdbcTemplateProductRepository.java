package gift.domain.product;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcTemplateProductRepository implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 해당 이름의 상품 DB 에 존재 여부 확인
     */
    public boolean existsByProductName(String name) {
        String sql = "SELECT COUNT(*) FROM product WHERE name = ?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, name);

        if (count == 1) {
            return true;
        }
        return false;
    }


}

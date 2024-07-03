package gift.global.validation;

import gift.global.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 유효성 검증 클래스
 */
@Component
public class Validator {

    private final JdbcTemplate jdbcTemplate;

    public Validator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void validateDuplicateProduct(String name) {
        String sql = "SELECT CASE WHEN EXISTS ("
                     + "    SELECT 1 FROM product WHERE name = ?"
                     + ") THEN TRUE ELSE FALSE END";

        Boolean result = jdbcTemplate.queryForObject(sql, Boolean.class, name);
        if (result) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "해당 이름의 상품이 이미 존재합니다.");
        }
    }
}

package gift.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * 로그인 DAO 클래스
 */
@Repository
public class LoginDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * LoginDAO 생성자
     *
     * @param jdbcTemplate JdbcTemplate 객체
     */
    public LoginDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 사용자 존재 여부 확인 메서드
     *
     * @param login 로그인 정보
     * @return 사용자 존재 여부
     */
    public boolean isExist(Login login) {
        String sql = "SELECT EXISTS(SELECT 1 FROM Users WHERE email = ? and password = ? and isDelete=1)";
        if (jdbcTemplate.queryForObject(sql, new Object[]{login.getEmail(), login.getPassword()}, Integer.class) == 1) {
            return true;
        }
        return false;
    }
}

package gift.domain.user;

import gift.domain.product.ProductDTO;
import gift.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository implements UserRepositoryInterface {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 해당 이메일 회원 DB 에 존재 여부 확인
     * @param email
     * @return 존재하면 true, 없으면 false, 여러명이면 error
     */
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);

        if(count > 1) throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 동일한 이름의 회원 2명 이상 존재");
        if(count == 1) return true;
        return false;
    }

    /**
     * 회원 가입
     * @param userDTO
     */
    public void join(UserDTO userDTO) {
        String sql = "INSERT INTO users (email, password) VALUES (?, ?)";
        int rowNum = jdbcTemplate.update(sql, userDTO.getEmail(), userDTO.getPassword());

        if (rowNum == 0) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "회원 가입에 실패했습니다");
        }
    }

    /**
     * 로그인 정보가 일치하는지 확인
     * @param userDTO
     * @return 일치하면 true, 그렇지 않으면 false
     */
    public boolean checkUserInfo(UserDTO userDTO) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, userDTO.getEmail(), userDTO.getPassword());
        return count != null && count > 0;
    }
}

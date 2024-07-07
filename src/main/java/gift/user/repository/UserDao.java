package gift.user.repository;

import gift.user.dto.UserDto;
import gift.user.entity.UserEntity;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

// users db와 통신하는 dao
@Repository
public class UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Create 처리 메서드
    public UserEntity insertUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity(userDto.email(), userDto.password());

        // 이미 존재하는 key인지 검증
        String email = userEntity.getEmail();
        verifyUserAlreadyExist(email);

        var sql = """
            insert into users (email, password) values (?, ?)
            """;

        // 반환값은 집어 넣기에 성공한 userEntity
        jdbcTemplate.update(sql, userEntity.getEmail(), userEntity.getPassword());
        return userEntity;
    }

    // Read 처리 메서드. 관리자 화면에서 사용할 수 있으나, 시간 상의 이유로 메서드만 만듦
    public List<UserEntity> selectUsers() {
        var sql = """
            select email, password
            from users;
            """;

        return jdbcTemplate.query(sql, (resultSet, rowNum) -> new UserEntity(
            resultSet.getString("email"),
            resultSet.getString("password")
        ));
    }

    // 비밀번호 Update 처리 메서드. 관리자 화면 혹은 비밀번호 변경에서 사용할 수 있으나, 시간 상의 이유로 메서드만 만듦
    public void updateUserPassword(UserDto userDto) {
        UserEntity UserEntity = new UserEntity(userDto.email(), userDto.password());

        var sql = """
            update users set password = ?, where email = ?;
            """;

        jdbcTemplate.update(sql, UserEntity.getPassword(), UserEntity.getEmail());
    }

    // Delete 처리 메서드. 관리자 화면 혹은 회원 탈퇴에서 사용할 수 있으나, 시간 상의 이유로 메서드만 만듦
    public void deleteUser(String email) {
        // 우선 해당하는 id가 있는지부터 검사
        verifyUserExist(email);

        var sql = """
            delete from users where email = ?;
            """;

        jdbcTemplate.update(sql, email);
    }

    // db에서 특정 key를 갖는 로우를 반환
    public UserEntity selectUser(String email) {
        // 존재하는 key를 불러올 수 있도록 검증
        verifyUserExist(email);

        var sql = """
            select email, password
            from users
            where email = ?;
            """;

        return jdbcTemplate.queryForObject(sql, (resultSet, rowNum) -> new UserEntity(
            resultSet.getString("email"),
            resultSet.getString("password")
        ), email);
    }

    // db에서 특정 key를 갖는 로우가 있는지 확인하는 메서드
    private boolean exists(String email) {
        var sql = """
            select email
            from users
            where email = ?;
            """;

        // 결과의 로우가 존재하는지 반환
        boolean isEmpty = jdbcTemplate.query(sql,
                (resultSet, rowNum) -> resultSet.getString("email"), email)
            .isEmpty();

        return !isEmpty;
    }

    // 논리적 검증: 삽입 시에는 key가 중복되지 않아야 한다.
    private void verifyUserAlreadyExist(String email) {
        if (exists(email)) {
            // 의미있는 메시지를 위해 id를 함께 제공
            throw new IllegalArgumentException("이미 존재하는 email입니다. (이메일 주소: " + email + ")");
        }
    }

    // 논리적 검증: 조회 시에는 key가 존재해야 한다.
    private void verifyUserExist(String email) {
        if (!exists(email)) {
            // 의미있는 메시지를 위해 id를 함께 제공
            throw new NoSuchElementException("회원가입된 email이 아닙니다. (이메일 주소: " + email + ")");
        }
    }
}

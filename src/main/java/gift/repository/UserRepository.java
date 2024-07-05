package gift.repository;

import gift.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean isExistAccount(String userId) {
        String sql = "select count(*) from user_tb where user_id=?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, userId);
        return count > 0;
    }

    public void saveUser(User user) {
        String sql = "insert into user_tb(id, user_id, user_pw) values(?,?,?)";
        jdbcTemplate.update(sql,user.getId(),user.getUserId(),user.getUserPw());
    }
}

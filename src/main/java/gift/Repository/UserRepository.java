package gift.Repository;

import gift.Model.Member;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate){
            this.jdbcTemplate = jdbcTemplate;
    }
    private final RowMapper<String> rowMapper = new BeanPropertyRowMapper<>(String.class);

    public int addUser(Member member){
        return jdbcTemplate.update("INSERT INTO wishuser (email) VALUES (?)",member.getEmail());
    }
    public String findUser(String email){
        //있나 없나 확인
        return jdbcTemplate.queryForObject("SELECT * FROM wishuser WHERE email = ?", rowMapper, email);
    }
}

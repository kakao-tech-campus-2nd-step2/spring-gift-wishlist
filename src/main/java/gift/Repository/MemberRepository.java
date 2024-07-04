package gift.Repository;

import gift.Model.Member;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Member> rowMapper = new BeanPropertyRowMapper<>(Member.class);

    public Member findByEmail(String email){
        return jdbcTemplate.queryForObject("SELECT * FROM member WHERE email = ?",rowMapper,email);
    }

}



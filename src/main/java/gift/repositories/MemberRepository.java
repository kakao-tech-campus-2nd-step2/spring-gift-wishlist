package gift.repositories;

import gift.Member;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    회원 찾기
    public Member find(Member member) {
        String email = member.getEmail();
        String password = member.getPassword();
        List<Member> queried = jdbcTemplate.query("SELECT * FROM members where email= '" + email +"' and password ='"+ password + "' limit 1", (resultSet, rowNum) ->
            new Member(
                resultSet.getString("email"),
                resultSet.getString("password")
            )
        );
        if (queried.size() == 0) {
            return null;
        }
        return queried.get(0);
    }

//    회원 가입
    public void register(Member member){
        jdbcTemplate.execute("INSERT INTO members (email, password) VALUES('" + member.getEmail() + "', '" + member.getPassword() + "');");
    }

}

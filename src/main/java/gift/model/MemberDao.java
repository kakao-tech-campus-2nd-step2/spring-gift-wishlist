package gift.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

//    //상품 테이블 생성
//    public void createMemberTable(){
//        var sql = """
//            create table member (
//            email varchar(255),
//            password varchar(255),
//            primary key (email)
//            )
//            """;
//        jdbcTemplate.execute(sql);
//    }

    public RowMapper<Member> MemberRowMapper(){
        return ((resultSet, rowNum) -> {
            Member member = new Member();
            member.setEmail(resultSet.getString("email"));
            member.setPassword(resultSet.getString("password"));
            return member;
        });
    }

    public Member selectMember(String email){
        var sql = "select email, password from member where email = ?";
        return jdbcTemplate.queryForObject(sql, MemberRowMapper(), email);
    }

    public void insertMember(Member member){
        var sql = "insert into member (email, password) values (?, ?)";
        jdbcTemplate.update(sql, member.getEmail(), member.getPassword());
    }
}

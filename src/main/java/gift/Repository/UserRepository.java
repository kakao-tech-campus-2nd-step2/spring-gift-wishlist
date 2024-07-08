package gift.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbctemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbctemplate) {
        this.jdbctemplate = jdbctemplate;
    }

    public void createUserTable() {
        var sql = """
                create table Users(
                id bigint auto_increment,
                email varchar(50),
                password varchar(50),
                primary key(id)
                )
                """;
        jdbctemplate.execute(sql);
    }
    public void insertUser(String email, String password){
        var sql = "insert into Users (email, password) values (?,?)";
        jdbctemplate.update(sql, email, password);
    }


}

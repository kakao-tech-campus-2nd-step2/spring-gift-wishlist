package gift.Repository;

import gift.Exception.ForbiddenException;
import gift.Model.Product;
import gift.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public String login(String email, String password) {
        var sql = "select password from Users where email = ? AND password = ?";
        try{
            return jdbctemplate.queryForObject(sql, new Object[]{email, password}, String.class);
        } catch (EmptyResultDataAccessException e){
            throw new ForbiddenException("잘못된 로그인입니다");
        }
    }


}

package gift.Controller;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

//app실행 했을 때 테이블 생성
@Service
public class InitCreateTable {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InitCreateTable(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void init() {
        //System.out.println("init");
        createProductTable();
        //System.out.println("create");
    }

    public void createProductTable() {
        var sql = """
            create table product (
              id bigint auto_increment,
              name varchar(255),
              price int,
              image_url varchar(255),
              primary key (id)
            )
            """;
        jdbcTemplate.execute(sql);
    }
}

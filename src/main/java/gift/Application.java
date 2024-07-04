package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class Application implements CommandLineRunner {
    // 필드 의존성 주입
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // CommandLineRunner의 메서드를 오버라이딩, 실행 시 아래 sql을 실행 시킨다 ( 데이터베이스 초기화 )
    @Override
    public void run(String... strings) throws Exception {

        jdbcTemplate.execute("DROP TABLE products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE products(" +
                             "id bigint, " +
                             "name VARCHAR(255), " +
                             "price bigint, " +
                             "imageUrl VARCHAR(255), "+
                             "primary key(id))");
        jdbcTemplate.execute("CREATE TABLE users(" +
                             "id bigint, " +
                             "email VARCHAR(255), " +
                             "password VARCHAR(255), "+
                             "primary key(id))");
    }
}

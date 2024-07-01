package gift;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    JdbcTemplate jdbcTemplate;

    public Application(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... strings) throws Exception{
        jdbcTemplate.execute("DROP TABLE menus IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE menus(id INT,name VARCHAR(255),price INT,imageUrl VARCHAR(2048))");
    }
}

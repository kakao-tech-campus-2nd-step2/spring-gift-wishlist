package gift;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    public Application (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        jdbcTemplate.execute("DROP TABLE products IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE products ("
            + "id LONG,"
            + " name VARCHAR(255),"
            + " price INT,"
            + " imageUrl VARCHAR(255),"
            + " PRIMARY KEY (id))"
        );
    }
}

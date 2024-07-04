package gift;

import gift.utility.DataBaseUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final DataBaseUtil dataBaseUtil;


    public Application(DataBaseUtil dataBaseUtil) {
        this.dataBaseUtil = dataBaseUtil;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) {
        dataBaseUtil.dropProductTable();
        dataBaseUtil.createProductTable();
    }
}

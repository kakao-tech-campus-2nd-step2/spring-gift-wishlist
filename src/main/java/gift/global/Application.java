package gift.global;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "gift")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

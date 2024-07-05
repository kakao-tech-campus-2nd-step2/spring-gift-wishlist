package gift;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        //어플이 실행되지마자 테이블 생성 해보려고하니 잘 되지 않습니다 ㅠㅠ
        //       JdbcProductController jpc =new JdbcProductController(ProductDao());
        //               jpc.createProductTable();
    }
}

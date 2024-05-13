package nu.zapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ApiStartUp {
    public static void main(String... args) {
        if (args != null && args.length > 0 && "TestDbMaker".equals(args[0])) {
            TestDbMaker.main(null); 
        } else {
            SpringApplication.run(ApiStartUp.class, args);
        }
    }
}

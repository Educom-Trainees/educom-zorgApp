package nu.zapp;

import nu.zapp.services.DatabaseScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaRepositories
@EnableScheduling
public class ApiStartUp {

    @Autowired
    private static TestDbFill dbFill;

    private static DatabaseScheduledService dbService;

    public ApiStartUp(TestDbFill dbFill, DatabaseScheduledService dbService) {
        this.dbService = dbService;
        this.dbFill = dbFill;
    }

    public static void main(String... args) {
        if (args != null && args.length > 0 && "TestDbMaker".equals(args[0])) {
            System.out.println("Start creating Database");
            System.setProperty("spring.jpa.ddl-auto", "create");
            System.setProperty("spring.datasource.username", System.getenv("MYSQL_USER"));
            System.setProperty("spring.datasource.password", System.getenv("MYSQL_PASSWORD"));
            SpringApplication.run(ApiStartUp.class, args);
            dbFill.fillDb();
            dbService.createInactivityCheckProcedure();
        } else {
            System.setProperty("spring.jpa.ddl-auto", "none");
            SpringApplication.run(ApiStartUp.class, args);
        }

    }
}

package nu.zapp.services;

import nu.zapp.ExceptionHandler.DatabaseFailedToConnect;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.stereotype.Component;

@Component
public class Crud {
    private static SessionFactory sessionFactory;
    public void setUpSessionFactory() {

        String mysqlUser = System.getenv("MYSQL_USER");
        String mysqlPassword = System.getenv("MYSQL_PASSWORD");

        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .applySetting("hibernate.connection.username", mysqlUser)
                    .applySetting("hibernate.connection.password", mysqlPassword)
                    .build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                //return sessionFactory;
            } catch (Exception e) {
                StandardServiceRegistryBuilder.destroy(registry);
                throw new DatabaseFailedToConnect();
                //System.out.println("Error setting up session factory: " + e.getMessage());
            }
        }
        //return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

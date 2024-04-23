package nu.zapp;

import org.hibernate.SessionFactory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class TestDbMaker {

    private static SessionFactory sessionFactory;
    public static void main(String... args) {
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
            } catch (Exception e) {
                System.out.println("Error setting up session factory: " + e.getMessage());
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }

    }

}
package nu.zapp;

import nu.zapp.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class testDbMaker {

    private static SessionFactory sessionFactory;
    public static void main(String... args) {
        try
        {

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
//            Configuration config=new Configuration();
//            config.configure();
//            System.out.println(config);
//            SessionFactory sessionFactory=config.buildSessionFactory();
            Session session=sessionFactory.openSession();
            System.out.println(session);
            Employee s=new Employee();
            s.setId(101);
            s.setUserName("Test");
            s.setPassword("Test");
            s.setPasswordExpiration(null);
            s.setFirstName("Test");
            s.setLastName("Test");
            s.setRole("worker");
            session.save(s);
            Transaction t=session.beginTransaction();
            t.commit();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
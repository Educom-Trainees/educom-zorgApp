package nu.zapp;

import nu.zapp.entities.Customer;
import nu.zapp.entities.Employee;
import nu.zapp.entities.Generaltasks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class TestDbMaker {

    private static SessionFactory sessionFactory;

    public static void main(String... args) {
        String mysqlUser = System.getenv("ZAPP_USER");
        String mysqlPassword = System.getenv("ZAPP_PASSWORD");

        if (sessionFactory == null) {
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                    .configure() //
//                    .applySetting("hibernate.hbm2ddl.auto", "create")
//                    .applySetting("hibernate.connection.username", mysqlUser)
//                    .applySetting("hibernate.connection.password", mysqlPassword)
                    .build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Error setting up session factory: " + e.getMessage());
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        System.out.println(session);
        session.save(makeEmployeeOne());
        session.save(makeEmployeeTwo());
        session.save(makeCustomerOne());
        session.save(makeCustomerTwo());
        session.save(makeCustomerThree());
        session.save(makeTaskOne());
        session.save(makeTaskTwo());
        transaction.commit();
        session.close();



    }

    private static Employee makeEmployeeOne(){
        Employee one=new Employee();
        //one.setId(101);
        one.setUserName("Een");
        one.setFirstName("Eendrecht");
        one.setLastName("Een");
        one.setRole("Employee");
        one.setAddress("Een");
        one.setPostalCode("1234Een");
        one.setResidence("EenStad");
        one.setMonday(true);
        one.setTuesday(true);
        one.setWednesday(false);
        one.setThursday(true);
        one.setFriday(false);
        return one;
    }

    private static Employee makeEmployeeTwo(){
        Employee two=new Employee();
        //two.setId(102);
        two.setUserName("Twee");
        two.setFirstName("Twee");
        two.setLastName("Twee");
        two.setRole("Employee");
        two.setAddress("Twee");
        two.setPostalCode("1234TW");
        two.setResidence("TweeStad");
        two.setMonday(true);
        two.setTuesday(false);
        two.setWednesday(false);
        two.setThursday(false);
        two.setFriday(true);
        return two;
    }

    private static Customer makeCustomerOne(){
        Customer one = new Customer();
        //one.setId(101);
        one.setFirstName("Een");
        one.setLastName("Eendart");
        one.setAddress("Eenstraat");
        one.setPostalCode("1234EN");
        one.setResidence("EenStad");
        one.setActive(true);
        return one;
    }

    private static Customer makeCustomerTwo(){
        Customer two = new Customer();
        //two.setId(102);
        two.setFirstName("Twee");
        two.setLastName("Tweedart");
        two.setAddress("Tweestraat");
        two.setPostalCode("1234TW");
        two.setResidence("TweeStad");
        two.setActive(true);
        return two;
    }

    private static Customer makeCustomerThree(){
        Customer three = new Customer();
        //three.setId(103);
        three.setFirstName("Drie");
        three.setLastName("Driedrecht");
        three.setAddress("Driestraat");
        three.setPostalCode("1234DR");
        three.setResidence("Driestad");
        three.setActive(false);
        return three;
    }

    private static Generaltasks makeTaskOne(){
        Generaltasks one = new Generaltasks();
        //one.setId(101);
        one.setTask("Opstaan uit bed");
        return one;
    }

    private static Generaltasks makeTaskTwo(){
        Generaltasks two = new Generaltasks();
        //two.setId(102);
        two.setTask("Naar bed brengen");
        return two;
    }

}
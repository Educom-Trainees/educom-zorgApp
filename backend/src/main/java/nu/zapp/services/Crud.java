package nu.zapp.services;

import nu.zapp.ExceptionHandler.DatabaseFailedToConnect;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class Crud {
    private static SessionFactory sessionFactory;
    private void setUpSessionFactory() {

        String mysqlUser = System.getenv("ZAPP_USER");
        String mysqlPassword = System.getenv("ZAPP_PASSWORD");

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
                throw new DatabaseFailedToConnect();
                //System.out.println("Error setting up session factory: " + e.getMessage());
            }
        }
        //return null;
    }

    private SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public <T> List<T> readMultipleRows(Class<T> entityClass, String queryString, String errorMessage){
        setUpSessionFactory();
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            List<T> queryResult = session.createQuery(queryString, entityClass)
                    .list();
            session.getTransaction().commit();
            return queryResult;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage, e);
        }
    }

    public <T> T readOneRow(Class<T> entityClass, String id, String errorMessage){
        setUpSessionFactory();
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            T queryResults = session.get(entityClass, id);
            session.getTransaction().commit();
            session.close();
            return queryResults;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorMessage);
        }
    }

    public <T> T createRow(T entity, String errorMessage){
        setUpSessionFactory(); // Ensure session factory is initialized
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, errorMessage);
        }
    }
}

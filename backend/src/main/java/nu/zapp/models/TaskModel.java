package nu.zapp.models;

import nu.zapp.entities.GeneralTasks;
import nu.zapp.services.Crud;
import org.aspectj.weaver.loadtime.Agent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskModel {

    @Autowired
    private final Crud crud;

    @Autowired
    public TaskModel(Crud crud) {
        this.crud = crud;
    }
    public List<GeneralTasks> getTasks() {
        crud.setUpSessionFactory(); // Ensure session factory is initialized
        try (Session session = crud.getSessionFactory().openSession()) {
            session.beginTransaction();
            String queryString = "select a from GeneralTasks a";
            List<GeneralTasks> tasks = session.createQuery(queryString, GeneralTasks.class)
                    .list();
            session.getTransaction().commit();
            return tasks;
        } catch (Exception e) {
            System.out.println("Error reading General Task List: " + e.getMessage());
            return null;
        }
    }
}

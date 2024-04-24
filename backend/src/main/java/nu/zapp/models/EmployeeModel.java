package nu.zapp.models;

import nu.zapp.entities.Employee;
import nu.zapp.entities.GeneralTasks;
import nu.zapp.services.Crud;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeModel {

    private final Crud crud;

    @Autowired
    public EmployeeModel(Crud crud) {
        this.crud = crud;
    }

    public List<Employee> getEmployees(){
        crud.setUpSessionFactory(); // Ensure session factory is initialized
        try (Session session = crud.getSessionFactory().openSession()) {
            session.beginTransaction();
            String queryString = "select a from Employee a";
            List<Employee> employees = session.createQuery(queryString, Employee.class)
                    .list();
            session.getTransaction().commit();
            return employees;
        } catch (Exception e) {
            System.out.println("Error reading Employee Task List: " + e.getMessage());
            return null;
        }
    }

    public Employee getEmployee(String id){
        return null;
    }
}

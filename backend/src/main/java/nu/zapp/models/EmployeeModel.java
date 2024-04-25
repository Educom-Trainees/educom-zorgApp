package nu.zapp.models;

import nu.zapp.entities.Employee;
import nu.zapp.entities.GeneralTasks;
import nu.zapp.services.Crud;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class EmployeeModel {

    private final Crud crud;

    @Autowired
    public EmployeeModel(Crud crud) {
        this.crud = crud;
    }

    public List<Employee> getEmployees(){
        String queryString = "select a from Employee a";
        return crud.getAll(Employee.class, queryString, "Error reading Employee List");
    }

    public Employee getEmployee(String id){
        return crud.getById(Employee.class, id, "Werknemer niet gevonden");
    }
}

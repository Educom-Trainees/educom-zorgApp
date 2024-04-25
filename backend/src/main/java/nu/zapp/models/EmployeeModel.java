package nu.zapp.models;

import nu.zapp.entities.Employee;
import nu.zapp.services.Crud;
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
        String queryString = "select a from Employee a";
        return crud.readMultipleRows(Employee.class, queryString, "Error reading Employee List");
    }

    public Employee getEmployee(String id){
        return crud.readOneRow(Employee.class, id, "Werknemer niet gevonden");
    }
}

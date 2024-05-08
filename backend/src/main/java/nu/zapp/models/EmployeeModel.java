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

    public List<Employee> readAllEmployees(){
        String queryString = "select a from Employee a";
        return crud.readMultipleRows(Employee.class, queryString, "Error reading Employee List");
    }

    public Employee readEmployeeById(int id){
        return crud.readOneRow(Employee.class, Integer.toString(id), "Werknemer niet gevonden");
    }

    public Employee createEmployee(Employee newEmployee) {
        // TODO validate employee    
        Employee createdEmployee = crud.createRow(newEmployee, "Medewerker kon niet gemaakt worden");
        return createdEmployee;
    }

    public void updateEmployee(Employee newEmployee) {
        // TODO validate
        crud.updateRow(newEmployee, "Medeweker kon niet worden bijgewerkt");
    }

}


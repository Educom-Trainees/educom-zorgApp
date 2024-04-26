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


    public boolean createEmployee(String userName, String password,
                                  String firstName, String lastName, String role,
                                  String address, String postalCode, String residence,
                                  boolean monday, boolean tuesday, boolean wednesday, boolean thursday, boolean friday) {
            Employee newEmployee = new Employee();
            newEmployee.setUserName(userName);
            newEmployee.setPassword(password);
            //On creation the password expiration would need to be calculated
            newEmployee.setFirstName(firstName);
            newEmployee.setLastName(lastName);
            newEmployee.setRole(role);
            newEmployee.setAddress(address);
            newEmployee.setPostalCode(postalCode);
            newEmployee.setResidence(residence);
            newEmployee.setMonday(monday);
            newEmployee.setTuesday(tuesday);
            newEmployee.setWednesday(wednesday);
            newEmployee.setThursday(thursday);
            newEmployee.setFriday(friday);
            Employee CreatedEmployee = crud.createRow(newEmployee, "Medewerker kon niet gemaakt worden");
            return CreatedEmployee != null;
        }
}


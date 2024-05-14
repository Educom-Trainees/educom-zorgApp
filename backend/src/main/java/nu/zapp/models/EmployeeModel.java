package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Employee;
import nu.zapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeModel {

    @Autowired
    private EmployeeRepository eRepository;

    public List<Employee> findAll(){
        return eRepository.findAll();
    }

    public Employee findById(int id){
        Employee employee = eRepository.findById(id);
        if (employee == null){
            throw new ExceptionNumId(id, "werknemer");
        }
        return employee;
    }

    public Employee findByUserName(String username){
        Employee employee = eRepository.findByUsername(username);
        if (employee == null){
            throw new ExceptionNumId(0, "werknemer");
        }
        return employee;
    }

    public Employee createEmployee(Employee newEmployee){
        // first have to check if username is occupied
        newEmployee.setId(0);
        if (eRepository.findByUsername(newEmployee.getuserName()) != null){
            throw new ExceptionItemExists("gebruikersnaam");
        }
        return eRepository.save(newEmployee);
    }

    public Employee updateEmployee(Employee updatedEmployee){
        return eRepository.save(updatedEmployee);
    }

}


package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionInvalidInput;
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
        userNameCheck(newEmployee.getuserName());
        newEmployee.setPostalCode(postalCodeCheck(newEmployee.getPostalCode()));
        return eRepository.save(newEmployee);
    }

    public Employee updateEmployee(Employee updatedEmployee){
        //TODO username check
        return eRepository.save(updatedEmployee);
    }

    private void userNameCheck(String username){
        if (eRepository.findByUsername(username) != null){
            throw new ExceptionItemExists("gebruikersnaam");
        }
    }

    private String postalCodeCheck(String postalcode){
        // This function will need to go to a generic place because both employees and customers can use it
        if(!postalcode.matches("^\\d{4}[a-zA-Z]{2}")){
            throw new ExceptionInvalidInput("postcode");
        }
        String postalcodeCap = postalcode.substring(0,4) + postalcode.substring(4, 6).toUpperCase();
        return postalcodeCap;
    }

}


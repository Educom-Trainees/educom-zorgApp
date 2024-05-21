package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionInvalidInput;
import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Employee;
import nu.zapp.repositories.EmployeeRepository;
import nu.zapp.services.UserPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class EmployeeModel {

    @Autowired
    private EmployeeRepository eRepository;
    private final UserPasswordService passwordService;
    private final PersonModel personModel;
    @Autowired
    public EmployeeModel(UserPasswordService passwordService, PersonModel personModel) {
        this.passwordService = passwordService;
        this.personModel = personModel;
    }

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
        newEmployee.setId(0);
        userNameCheck(newEmployee.getUsername());
        newEmployee.setPostalcode(personModel.postalCodeCheck(newEmployee.getPostalcode()));
        String encodedPassword = passwordEncryption(newEmployee.getPassword());
        newEmployee.setPassword(encodedPassword);
        return eRepository.save(newEmployee);
    }

    public Employee updateEmployee(Employee updatedEmployee){
        Employee oldEmployee = findById(updatedEmployee.getId());
        if (!Objects.equals(oldEmployee.getUsername(), updatedEmployee.getUsername())){
            userNameCheck(updatedEmployee.getUsername());
        }
        updatedEmployee.setPostalcode(personModel.postalCodeCheck(updatedEmployee.getPostalcode()));
        return eRepository.save(updatedEmployee);
    }

    private void userNameCheck(String username){
        if (eRepository.findByUsername(username) != null){
            throw new ExceptionItemExists("gebruikersnaam");
        }
    }

    private String passwordEncryption(String password){
        return passwordService.encodePassword(password);
    }

}


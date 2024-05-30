package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Employee;
import nu.zapp.entities.WorkSchedule;
import nu.zapp.repositories.EmployeeRepository;
import nu.zapp.repositories.WorkScheduleRepository;
import nu.zapp.services.UserPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class EmployeeModel {

    @Autowired
    private EmployeeRepository eRepository;
    @Autowired
    private WorkScheduleRepository wsRepository;

    @Autowired
    private UserPasswordService passwordService;

    @Autowired
    private PersonModel personModel;

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
    @Transactional
    public Employee createEmployee(Employee newEmployee){
        newEmployee.setId(0);
        newEmployee.setPassword("SummerSpring");
        List<WorkSchedule> newWorkSchedule = newEmployee.getWorkSchedule();
        newEmployee.setWorkSchedule(null);
        userNameCheck(newEmployee.getUsername());
        newEmployee.setPostalcode(personModel.postalCodeCheck(newEmployee.getPostalcode()));
        String encodedPassword = passwordEncryption(newEmployee.getPassword());
        newEmployee.setPassword(encodedPassword);
        Employee savedEmployee =  eRepository.save(newEmployee);
        if (newWorkSchedule != null){
            savedEmployee.setWorkSchedule(setWorkScheduleIds(savedEmployee, newWorkSchedule));
            return eRepository.save(savedEmployee);
        }
        return savedEmployee;
    }

    private List<WorkSchedule> setWorkScheduleIds(Employee savedEmployee, List<WorkSchedule> newWorkSchedule) {
        for (WorkSchedule schedule : newWorkSchedule) {
            schedule.setEmployee(savedEmployee);
            schedule.setId(0);
        }
        return newWorkSchedule;
    }

    @Transactional
    public Employee updateEmployee(Employee updatedEmployee){
        Employee oldEmployee = findById(updatedEmployee.getId());
        List<WorkSchedule> oldWorkSchedule = oldEmployee.getWorkSchedule();
        List<WorkSchedule> updatedWorkSchedule = updatedEmployee.getWorkSchedule();
        updatedEmployee.setWorkSchedule(null);
        deleteOldWorkScheme(oldWorkSchedule);
        // Changing username should go to a seperate place
//        if (!Objects.equals(oldEmployee.getUsername(), updatedEmployee.getUsername())){
//            userNameCheck(updatedEmployee.getUsername());
//        }
        updatedEmployee.setWorkSchedule(setWorkScheduleIds(updatedEmployee, updatedWorkSchedule));
        updatedEmployee.setUsername(oldEmployee.getUsername());
        updatedEmployee.setPassword(oldEmployee.getPassword());
        updatedEmployee.setPostalcode(personModel.postalCodeCheck(updatedEmployee.getPostalcode()));
        return eRepository.save(updatedEmployee);
    }

    private void deleteOldWorkScheme(List<WorkSchedule> schedule){
        for (WorkSchedule oldSchedule : schedule) {
            wsRepository.delete(oldSchedule);
        }
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


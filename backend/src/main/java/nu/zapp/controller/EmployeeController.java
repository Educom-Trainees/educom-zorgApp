package nu.zapp.controller;


import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Employee;
import nu.zapp.repositories.EmployeeRepository;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository eRepository;
    @CrossOrigin()
    @GetMapping("")
    List<Employee> getEmployees(){
        return eRepository.findAll();
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable int id){
        Employee employee = eRepository.findById(id);
        if (employee == null){
            throw new ExceptionNumId(id, "werknemer");
        }
        return employee;
    }

    @CrossOrigin()
    @GetMapping("/by-username/{username}")
    Employee getEmployee(@PathVariable String username){
        Employee employee = eRepository.findByUserName(username);
        if (employee == null){
            throw new ExceptionNumId(0, "werknemer");
        }
        return employee;
    }


    @CrossOrigin()
    @PostMapping("/employees")
    Employee postEmployee(@RequestBody Employee newEmployee){
        return eRepository.save(newEmployee);
    }

    @CrossOrigin()
    @PutMapping("/employees/{id}")
    void updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmployee){
        if (id != updatedEmployee.getId()) {
            throw new ExceptionNumId(id, "werknemer");
        }
        eRepository.save(updatedEmployee);
    }
}

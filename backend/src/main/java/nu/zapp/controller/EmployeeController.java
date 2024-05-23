package nu.zapp.controller;

import nu.zapp.entities.Employee;
import nu.zapp.models.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeModel eModel;
    @CrossOrigin()
    @GetMapping("")
    List<Employee> getEmployees(){
        return eModel.findAll();
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    Employee getEmployee(@PathVariable int id){
        return eModel.findById(id);
    }

    @CrossOrigin()
    @GetMapping("/by-username/{username}")
    Employee getEmployee(@PathVariable String username){
        return eModel.findByUserName(username);
    }


    @CrossOrigin()
    @PostMapping("")
    Employee postEmployee(@RequestBody Employee newEmployee){
        return eModel.createEmployee(newEmployee);
    }

    @CrossOrigin()
    @PutMapping("")
    Employee updateEmployee(@RequestBody Employee updatedEmployee){
        return eModel.updateEmployee(updatedEmployee);
    }
}

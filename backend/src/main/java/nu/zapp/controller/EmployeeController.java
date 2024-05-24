package nu.zapp.controller;

import nu.zapp.entities.Employee;
import nu.zapp.mappers.EmployeeMapper;
import nu.zapp.models.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeModel eModel;
    @Autowired
    private EmployeeMapper mapper;

    @CrossOrigin()
    @GetMapping("")
    Object getEmployees(@RequestParam(value="id", required = false)Integer id){
        if (id != null){
            return eModel.findById(id);
        }
        return mapper.sourceToDestination(eModel.findAll());
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

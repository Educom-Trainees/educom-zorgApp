package nu.zapp.controller;

import nu.zapp.DTO.EmployeeDetailDTO;
import nu.zapp.entities.Employee;
import nu.zapp.mappers.EmployeeDetailMapper;
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
    @Autowired
    private EmployeeDetailMapper dMapper;

    @CrossOrigin()
    @GetMapping("")
    Object getEmployees(@RequestParam(value="id", required = false)Integer id){
        if (id != null){
            return dMapper.sourceToDestination(eModel.findById(id));
        }
        return mapper.sourceToDestination(eModel.findAll());
    }

    @CrossOrigin()
    @PostMapping("")
    Employee postEmployee(@RequestBody EmployeeDetailDTO newEmployee){
        Employee employee = dMapper.destinationToSource(newEmployee);
        return eModel.createEmployee(employee);
    }

    @CrossOrigin()
    @PutMapping("")
    EmployeeDetailDTO updateEmployee(@RequestBody EmployeeDetailDTO updatedEmployee){
        Employee employee = dMapper.destinationToSource(updatedEmployee);
        return dMapper.sourceToDestination(eModel.updateEmployee(employee));
    }
}

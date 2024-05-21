package nu.zapp.controller;

import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Customer;
import nu.zapp.entities.Employee;
import nu.zapp.models.CustomerModel;
import nu.zapp.repositories.CustomerRepository;
import nu.zapp.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/customers")
public class CustomersController {
    @Autowired
    private CustomerModel cModel;

    @CrossOrigin()
    @GetMapping("")
    List<Customer> getCustomers(){
        return cModel.findAll();
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    Customer getCustomer(@PathVariable int id){
        return cModel.findById(id);
    }

    @CrossOrigin()
    @PostMapping("")
    Customer postCustomer(@RequestBody Customer newCustomer){
        return cModel.createCustomer(newCustomer);
    }

    @CrossOrigin()
    @PutMapping("")
    Customer putCustomer(@RequestBody Customer updateCustomer) { return cModel.updateCustomer(updateCustomer); }

}

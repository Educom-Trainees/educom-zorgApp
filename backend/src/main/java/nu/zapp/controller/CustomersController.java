package nu.zapp.controller;

import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Customer;
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
    private CustomerRepository cRepository;

    @CrossOrigin()
    @GetMapping("")
    List<Customer> getCustomers(){
        return cRepository.findAll();
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    Customer getCustomer(@PathVariable int id){
        Customer customer = cRepository.findById(id);
        if (customer == null){
            throw new ExceptionNumId(id, "klant");
        }
        return customer;
    }

}

package nu.zapp.controller;

import nu.zapp.DTO.CustomerDTO;
import nu.zapp.DTO.CustomerDetailDTO;
import nu.zapp.entities.Customer;
import nu.zapp.mappers.CustomerDetailMapper;
import nu.zapp.mappers.CustomerMapper;
import nu.zapp.models.CustomerModel;
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

    @Autowired
    private CustomerMapper mapper;

    @Autowired
    private CustomerDetailMapper dMapper;

    @CrossOrigin()
    @GetMapping("")
    List<CustomerDTO> getCustomers(){
        return mapper.sourceToDestination(cModel.findAll());
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    CustomerDetailDTO getCustomer(@PathVariable int id){
        return dMapper.sourceToDestination(cModel.findById(id));
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

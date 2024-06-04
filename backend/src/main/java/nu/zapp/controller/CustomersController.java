package nu.zapp.controller;

import nu.zapp.DTO.CustomerDetailDTO;
import nu.zapp.entities.Customer;
import nu.zapp.mappers.CustomerDetailMapper;
import nu.zapp.mappers.CustomerMapper;
import nu.zapp.models.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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

    //Object is unspecified, depends on @RequestParams  (commented 2024-06-03)
    @CrossOrigin()
    @GetMapping("")
    Object getCustomers(@RequestParam(value="id", required = false) Integer id){
        if (id!= null){
            return dMapper.sourceToDestination(cModel.findById(id));
        }
        return mapper.sourceToDestination(cModel.findAll());
    }

    @CrossOrigin()
    @PostMapping("")
    CustomerDetailDTO postCustomer(@RequestBody CustomerDetailDTO newCustomer){
        Customer customer = dMapper.destinationToSource(newCustomer);
        return dMapper.sourceToDestination(cModel.createCustomer(customer));
    }

    @CrossOrigin()
    @PutMapping("")
    CustomerDetailDTO putCustomer(@RequestBody CustomerDetailDTO updateCustomer) {
        Customer customer = dMapper.destinationToSource(updateCustomer);
        return dMapper.sourceToDestination(cModel.updateCustomer(customer)); }

}

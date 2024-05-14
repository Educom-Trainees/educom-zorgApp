package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Customer;
import nu.zapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerModel {

    @Autowired
    private CustomerRepository cRepository;

    public List<Customer> findAll(){ return cRepository.findAll();}

    public Customer findById(int id){
        Customer customer = cRepository.findById(id);
        if (customer == null){
            throw new ExceptionNumId(id, "klant");
        }
        return customer;
    }

    public Customer createCustomer(Customer newCustomer){
        return cRepository.save(newCustomer);
    }

}



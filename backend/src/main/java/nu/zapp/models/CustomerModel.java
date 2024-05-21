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
    private final PersonModel personModel;

    @Autowired
    public CustomerModel(PersonModel personModel) {
        this.personModel = personModel;
    }

    public List<Customer> findAll(){ return cRepository.findAll();}

    public Customer findById(int id){
        Customer customer = cRepository.findById(id);
        if (customer == null){
            throw new ExceptionNumId(id, "klant");
        }
        return customer;
    }

    public Customer createCustomer(Customer newCustomer){
        newCustomer.setId(0);
        newCustomer.setPostalcode(personModel.postalCodeCheck(newCustomer.getPostalcode()));
        return cRepository.save(newCustomer);
    }

    public Customer updateCustomer(Customer updatedCustomer){
        updatedCustomer.setPostalcode(personModel.postalCodeCheck(updatedCustomer.getPostalcode()));
        return cRepository.save(updatedCustomer);
    }

}



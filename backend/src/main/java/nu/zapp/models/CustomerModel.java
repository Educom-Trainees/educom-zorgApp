package nu.zapp.models;

import jakarta.transaction.Transactional;
import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Appointment;
import nu.zapp.entities.AppointmentTasks;
import nu.zapp.entities.Customer;
import nu.zapp.entities.CustomerTasks;
import nu.zapp.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerModel {

    @Autowired
    private CustomerRepository cRepository;
    @Autowired
    private PersonModel personModel;
    @Autowired
    private CustomerTaskModel cTaskModel;

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
        deleteOldCustomerTasks(updatedCustomer.getId());
        updatedCustomer.setPostalcode(personModel.postalCodeCheck(updatedCustomer.getPostalcode()));
        updatedCustomer.setCustomerTasks(setCustomerTaskIds(updatedCustomer));
        return cRepository.save(updatedCustomer);
    }

    private void deleteOldCustomerTasks(int id) {
        cTaskModel.deleteByCustomerId(id);
    }

    private List<CustomerTasks> setCustomerTaskIds(Customer customer ){
        List<CustomerTasks> taskList = customer.getCustomerTasks();
        customer.setCustomerTasks(null);
        for (CustomerTasks task : taskList) {
            task.setCustomer(customer);
            task.setId(0);
        }
        return taskList;
    }

}



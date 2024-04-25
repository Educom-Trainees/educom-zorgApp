package nu.zapp.models;

import nu.zapp.entities.Customer;
import nu.zapp.services.Crud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerModel {

    private final Crud crud;

    @Autowired
    public CustomerModel(Crud crud) {
        this.crud = crud;
    }

    public List<Customer> getCustomers() {
        String queryString = "select a from Customer a";
        return crud.readMultipleRows(Customer.class, queryString, "Error reading Customer List");
    }

    public Customer getCustomer(String id){
        return crud.readOneRow(Customer.class, id, "Klant niet gevonden");
    }

    public boolean createCustomer(String firstname, String lastname,
                                  String address, String postalcode,
                                  String residence, boolean active){
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstname);
        newCustomer.setLastName(lastname);
        newCustomer.setAddress(address);
        newCustomer.setPostalCode(postalcode);
        newCustomer.setResidence(residence);
        newCustomer.setActive(active);
        Customer CreatedCustomer = crud.createRow(newCustomer, "Klant kon niet worden gemaakt");
        return CreatedCustomer != null;
    }
}



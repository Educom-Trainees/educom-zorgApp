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

    public Customer getCustomer(int id){
        return crud.readOneRow(Customer.class, Integer.toString(id), "Klant niet gevonden");
    }

    public Customer createCustomer( Customer newCustomer){
        // TODO validate
        Customer createdCustomer = crud.createRow(newCustomer, "Klant kon niet worden gemaakt");
        return createdCustomer;
    }

    public void updateCustomer( Customer updateCustomer) {
        // TODO validate
        crud.updateRow(updateCustomer, "Klant kon niet worden bijgewerkt");
    }


}



package nu.zapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer extends Person{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerTasks> customerTasks;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String address, String postalcode, String residence, boolean active) {
        super(id, firstName, lastName, address, postalcode, residence, active);
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<CustomerTasks> getCustomerTasks() {
        return customerTasks;
    }

    public void setCustomerTasks(List<CustomerTasks> customerTasks) {
        this.customerTasks = customerTasks;
    }
}

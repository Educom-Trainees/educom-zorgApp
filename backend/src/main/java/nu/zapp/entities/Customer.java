package nu.zapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Customer extends Person{

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CustomerTasks> customerTasks;

    @Column(length=8)
    private LocalDate inactivationDate;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String address, String postalcode, String residence,
                    List<Appointment> appointments, List<CustomerTasks> customerTasks, LocalDate inactivationDate) {
        super(id, firstName, lastName, address, postalcode, residence);
        this.appointments = appointments;
        this.customerTasks = customerTasks;
        this.inactivationDate = inactivationDate;
    }

    @JsonIgnore
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

    public LocalDate getInactivationDate() {
        return inactivationDate;
    }

    public void setInactivationDate(LocalDate inactivationDate) {
        this.inactivationDate = inactivationDate;
    }
}

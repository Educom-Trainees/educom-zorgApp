package nu.zapp.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Customer extends Person{

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String address, String postalcode, String residence, boolean active) {
        super(id, firstName, lastName, address, postalcode, residence, active);
    }

}

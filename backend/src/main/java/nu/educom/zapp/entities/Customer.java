package nu.educom.zapp.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String address;

    private String postalCode;

    private String residence;

    public Customer() {
    }

    public Customer(int id, String firstName, String lastName, String address, String postalCode, String residence) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.residence = residence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}

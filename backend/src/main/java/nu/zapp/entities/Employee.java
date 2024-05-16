package nu.zapp.entities;

import jakarta.persistence.*;
import org.hibernate.jdbc.Work;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    @Column(length = 30, nullable = false)
    private String username;
    @Column(length = 60, nullable = false)
    //@JsonIgnore
    private String password;
    private LocalDateTime passwordExpiration;
    @Column(length=50, nullable = false)
    private String firstName;
    @Column(length=50, nullable = false)
    private String lastName;
    @Column(length=10)
    private String role;

    @Column(length=50, nullable = false)
    private String address;

    @Column(length=6, nullable = false)
    private String postalcode;

    @Column(length=50, nullable = false)
    private String residence;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active;
    

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<EmployeeToAppointment> employeeToAppointments;



    public Employee() {
    }

    public Employee(int id, String username, String password, LocalDateTime passwordExpiration, 
                    String firstName, String lastName, String role, 
                    String address, String postalcode, String residence, 
                    boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordExpiration = passwordExpiration;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.address = address;
        this.postalcode = postalcode;
        this.residence = residence;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getPasswordExpiration() {
        return passwordExpiration;
    }

    public void setPasswordExpiration(LocalDateTime passwordExpiration) {
        this.passwordExpiration = passwordExpiration;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

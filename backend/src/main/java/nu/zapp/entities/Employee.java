package nu.zapp.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Employee extends Person {

    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    private LocalDateTime passwordExpiration;

    @Column(length = 10)
    private String role;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkSchedule> workSchedule;

    public Employee() {
    }

    public Employee(int id, String username, String password, LocalDateTime passwordExpiration, String firstName, String lastName, String role, String address, String postalcode, String residence, boolean active) {
        super(id, firstName, lastName, address, postalcode, residence, active);
        this.username = username;
        this.password = password;
        this.passwordExpiration = passwordExpiration;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<WorkSchedule> getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(List<WorkSchedule> workSchedule) {
        this.workSchedule = workSchedule;
    }
}

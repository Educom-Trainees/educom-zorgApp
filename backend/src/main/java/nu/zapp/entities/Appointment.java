package nu.zapp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private LocalDate date;

    @Column()
    private LocalTime startTime;

    @Column()
    private LocalTime endTime;

    @Column()
    private LocalTime registerTime;

    @Column()
    private LocalTime logoutTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Appointment() {
    }

    public Appointment(int id, Customer customer, LocalDate date, LocalTime startTime, LocalTime endTime,
                       LocalTime registerTime, LocalTime logoutTime, Employee employee) {
        this.id = id;
        this.customer = customer;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registerTime = registerTime;
        this.logoutTime = logoutTime;
        this.employee = employee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalTime registerTime) {
        this.registerTime = registerTime;
    }

    public LocalTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalTime logoutTime) {
        this.logoutTime = logoutTime;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

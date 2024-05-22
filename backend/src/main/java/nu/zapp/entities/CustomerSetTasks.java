package nu.zapp.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity

public class CustomerSetTasks{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String task;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @Column()
    private LocalTime startTime;

    @Column()
    private LocalTime endTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String note;

    public CustomerSetTasks() {
    }

    public CustomerSetTasks(int id, String task, boolean active, LocalTime startTime, LocalTime endTime, Customer customer, String note) {
        this.id = id;
        this.task = task;
        this.active = active;
        this.startTime = startTime;
        this.endTime = endTime;
        this.customer = customer;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

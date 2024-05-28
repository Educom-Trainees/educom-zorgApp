package nu.zapp.entities;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(length=9)
    private String day;

    @Column()
    private LocalTime start_shift;

    @Column()
    private LocalTime end_shift;

    public WorkSchedule() {
    }

    public WorkSchedule(int id, Employee employee, String day, LocalTime start_shift, LocalTime end_shift) {
        this.id = id;
        this.employee = employee;
        this.day = day;
        this.start_shift = start_shift;
        this.end_shift = end_shift;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public LocalTime getStart_shift() {
        return start_shift;
    }

    public void setStart_shift(LocalTime start_shift) {
        this.start_shift = start_shift;
    }

    public LocalTime getEnd_shift() {
        return end_shift;
    }

    public void setEnd_shift(LocalTime end_shift) {
        this.end_shift = end_shift;
    }
}

package nu.zapp.entities;

import jakarta.persistence.*;

@Entity
public class EmployeeToAppointment {

    // Temporary id to make code function
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @OneToOne
    @JoinColumn(name = "appointment_id", referencedColumnName = "id")
    private Appointment appointment;

    public EmployeeToAppointment() {
    }

    public EmployeeToAppointment(Employee employee, Appointment appointment) {
        this.employee = employee;
        this.appointment = appointment;
    }
}

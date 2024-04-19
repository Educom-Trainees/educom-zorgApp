package nu.educom.zapp.entities;

import jakarta.persistence.JoinColumn;

public class EmployeeToAppointment {
    @JoinColumn
    private int employeeId;
    @JoinColumn
    private int appointmentId;

    public EmployeeToAppointment() {
    }

    public EmployeeToAppointment(int employeeId, int appointmentId) {
        this.employeeId = employeeId;
        this.appointmentId = appointmentId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
}

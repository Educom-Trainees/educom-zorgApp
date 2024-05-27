package nu.zapp.DTO;

import nu.zapp.entities.AppointmentTasks;
import nu.zapp.entities.CustomerTasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentDetailDTO extends AppointmentDTO {

    private int customerId;
    private int employeeId;
    private String employeeName;
    private String employeeAddress;
    private List<CustomerTasks> customerTasks;
    private List<AppointmentTasks> appointmentTasks;

    public AppointmentDetailDTO() {
    }

    public AppointmentDetailDTO(int id, LocalDate date, LocalTime startTime, LocalTime endTime, LocalTime registerTime, LocalTime logoutTime,
                                String name, String location, int customerId, int employeeId, String employeeName, String employeeAddress,
                                List<CustomerTasks> customerTasks, List<AppointmentTasks> appointmentTasks) {
        super(id, date, startTime, endTime, registerTime, logoutTime, name, location);
        this.customerId = customerId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeAddress = employeeAddress;
        this.customerTasks = customerTasks;
        this.appointmentTasks = appointmentTasks;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    public List<CustomerTasks> getCustomerTasks() {
        return customerTasks;
    }

    public void setCustomerTasks(List<CustomerTasks> customerTasks) {
        this.customerTasks = customerTasks;
    }

    public List<AppointmentTasks> getAppointmentTasks() {
        return appointmentTasks;
    }

    public void setAppointmentTasks(List<AppointmentTasks> appointmentTasks) {
        this.appointmentTasks = appointmentTasks;
    }
}

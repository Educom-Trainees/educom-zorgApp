package nu.zapp.DTO;

import nu.zapp.entities.AppointmentTasks;
import nu.zapp.entities.CustomerTasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentDetailDTO extends AppointmentDTO {

    private int customer_id;
    private int employee_id;
    private String employee_name;
    private String employee_address;
    private List<CustomerTasks> customer_tasks;
    private List<AppointmentTasks> appointment_tasks;

    public AppointmentDetailDTO() {
    }

    public AppointmentDetailDTO(int id, LocalDate date, LocalTime start_time, LocalTime end_time,
                                LocalTime register_time, LocalTime logout_time,
                                String name, String location, int customer_id, int employee_id,
                                String employee_name, String employee_address,
                                List<CustomerTasks> customer_tasks, List<AppointmentTasks> appointment_tasks) {
        super(id, date, start_time, end_time, register_time, logout_time, name, location);
        this.customer_id = customer_id;
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.employee_address = employee_address;
        this.customer_tasks = customer_tasks;
        this.appointment_tasks = appointment_tasks;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_address() {
        return employee_address;
    }

    public void setEmployee_address(String employee_address) {
        this.employee_address = employee_address;
    }

    public List<CustomerTasks> getCustomer_tasks() {
        return customer_tasks;
    }

    public void setCustomer_tasks(List<CustomerTasks> customer_tasks) {
        this.customer_tasks = customer_tasks;
    }

    public List<AppointmentTasks> getAppointment_tasks() {
        return appointment_tasks;
    }

    public void setAppointment_tasks(List<AppointmentTasks> appointment_tasks) {
        this.appointment_tasks = appointment_tasks;
    }
}

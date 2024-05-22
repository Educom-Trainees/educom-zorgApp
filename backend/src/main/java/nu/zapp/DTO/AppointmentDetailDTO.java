package nu.zapp.DTO;

import nu.zapp.entities.AppointmentTasks;
import nu.zapp.entities.CustomerTasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AppointmentDetailDTO extends AppointmentDTO {

    private int customer_id;
    private int employee_id;
    private List<CustomerTasks> customer_tasks;
    private List<AppointmentTasks> appointment_tasks;

    public AppointmentDetailDTO() {
    }

    public AppointmentDetailDTO(int id, LocalDate date, LocalTime start_time, LocalTime end_time, String name,
                                String location, int customer_id, int employee_id,
                                List<CustomerTasks> customer_tasks, List<AppointmentTasks> appointment_tasks) {
        super(id, date, start_time, end_time, name, location);
        this.customer_id = customer_id;
        this.employee_id = employee_id;
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

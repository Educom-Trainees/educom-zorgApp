package nu.zapp.controller;

import nu.zapp.entities.Appointment;
import nu.zapp.entities.Customer;
import nu.zapp.entities.Employee;
import nu.zapp.entities.generalTasks;
import nu.zapp.models.AppointmentModel;
import nu.zapp.models.CustomerModel;
import nu.zapp.models.EmployeeModel;
import nu.zapp.models.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ApiController {
    @Autowired
    private AppointmentModel aModel;
    @Autowired
    private TaskModel tModel;
    @Autowired
    private EmployeeModel mModel;
    @Autowired
    private CustomerModel cModel;

    @GetMapping("/calender")
    List<Appointment> getCalender() {
        return aModel.getCalender();
    }


    //Not sure on the url of the next two. They're meant to return the appointments an employee has
    @GetMapping("/appointment/{employee}")
    List<Appointment> getAppointments(@PathVariable String employee) {
        return aModel.getAppointments(employee);
    }

    // While this one is for pulling up info on a single appointment for the detail page
    @GetMapping("/appointment/{appointmentId}")
    Appointment getAppointmentDetails(@PathVariable String appointmentId){
        return aModel.getAppointmentDetails(appointmentId);
    }

    @GetMapping("/tasks")
    List<generalTasks> getTasks(){
        return tModel.getTasks();
    }

    @GetMapping("/employees")
    List<Employee> getEmployees(){
        return mModel.getEmployees();
    }

    @GetMapping("employees/{id}")
    Employee getEmployee(@PathVariable String id){
        return mModel.getEmployee(id);
    }

    @GetMapping("/customers")
    List<Customer> getCustomers(){
        return cModel.getCustomers();
    }

    @GetMapping("/customers/{id}")
    Customer getCustomer(@PathVariable String id){
        return cModel.getCustomer(id);
    }


}

package nu.zapp.controller;

import nu.zapp.ExceptionHandler.CustomerExceptionIdNum;
import nu.zapp.ExceptionHandler.EmployeeExceptionIdNum;
import nu.zapp.entities.Appointment;
import nu.zapp.entities.Customer;
import nu.zapp.entities.Employee;
import nu.zapp.entities.GeneralTasks;
import nu.zapp.models.AppointmentModel;
import nu.zapp.models.CustomerModel;
import nu.zapp.models.EmployeeModel;
import nu.zapp.models.TaskModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @CrossOrigin()
    @GetMapping("/employees")
    List<Employee> getEmployees(){
        return mModel.getEmployees();
    }

    @CrossOrigin()
    @GetMapping("employees/{id}")
    Employee getEmployee(@PathVariable String id){
        Employee employee = mModel.getEmployee(id);
        if (employee == null){
            throw new EmployeeExceptionIdNum(id);
        }
        return employee;
    }

    @CrossOrigin()
    @GetMapping("/customers")
    List<Customer> getCustomers(){
        return cModel.getCustomers();
    }

    @CrossOrigin()
    @GetMapping("/customers/{id}")
    Customer getCustomer(@PathVariable String id){
        Customer customer =  cModel.getCustomer(id);
        if (customer == null){
            throw new CustomerExceptionIdNum(id);
        }
        return customer;
    }

    @CrossOrigin()
    @PostMapping("/customers")
    boolean auth(@RequestParam("firstname") String firstName,
                 @RequestParam("lastname") String lastName,
                 @RequestParam("address") String address,
                 @RequestParam("postalcode") String postalCode,
                 @RequestParam("residence") String residence,
                 @RequestParam("active") boolean active){
        return cModel.createCustomer(firstName, lastName, address, postalCode, residence, active);
    }

    @CrossOrigin()
    @GetMapping("/tasks")
    List<GeneralTasks> getTasks(){

        return tModel.getTasks();
    }

    @CrossOrigin()
    @PostMapping("/tasks")
    boolean auth (@RequestParam("task") String task){
        return tModel.createTasks(task);
    }

}

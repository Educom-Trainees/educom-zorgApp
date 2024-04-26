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

import java.util.List;

@SpringBootApplication
@RestController
public class ApiController {
    @Autowired
    private AppointmentModel aModel;
    @Autowired
    private TaskModel tModel;
    @Autowired
    private EmployeeModel eModel;
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
        return eModel.getEmployees();
    }

    @CrossOrigin()
    @GetMapping("/employees/{id}")
    Employee getEmployee(@PathVariable String id){
        Employee employee = eModel.getEmployee(id);
        if (employee == null){
            throw new EmployeeExceptionIdNum(id);
        }
        return employee;
    }

    @CrossOrigin()
    @PostMapping("/employees")
    boolean authEmployee(@RequestParam("username") String userName,
                         @RequestParam("password") String password,
                         @RequestParam("firstname") String firstName,
                         @RequestParam("lastname") String lastName,
                         @RequestParam("role") String role,
                         @RequestParam("address") String address,
                         @RequestParam("postalcode") String postalCode,
                         @RequestParam("residence") String residence,
                         @RequestParam("monday") boolean monday,
                         @RequestParam("tuesday") boolean tuesday,
                         @RequestParam("wednesday") boolean wednesday,
                         @RequestParam("thursday") boolean thursday,
                         @RequestParam("friday") boolean friday){
    return eModel.createEmployee(userName, password, firstName, lastName, role, address, postalCode, residence, monday, tuesday, wednesday, thursday, friday);
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
    boolean authCustomer(@RequestParam("firstname") String firstName,
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
    boolean authTask (@RequestParam("task") String task){
        return tModel.createTasks(task);
    }

}

package nu.zapp.controller;

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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/employees")
    List<Employee> getEmployees(){
        return mModel.getEmployees();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("employees/{id}")
    Employee getEmployee(@PathVariable String id){
        Employee employee = mModel.getEmployee(id);
        if (employee == null){
            throw new EmployeeExceptionIdNum(id);
        }
        return employee;
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/customers")
    List<Customer> getCustomers(){
        List<Customer> customers = new ArrayList<>();

        Customer one = new Customer();
        one.setId(101);
        one.setFirstName("Een");
        one.setLastName("Eendart");
        one.setAddress("Eenstraat");
        one.setPostalCode("1234EN");
        one.setResidence("EenStad");
        one.setActive(true);

        Customer two = new Customer();
        two.setId(102);
        two.setFirstName("Twee");
        two.setLastName("Tweedart");
        two.setAddress("Tweestraat");
        two.setPostalCode("1234TW");
        two.setResidence("TweeStad");
        two.setActive(true);

        Customer three = new Customer();
        three.setId(103);
        three.setFirstName("Drie");
        three.setLastName("Driedrecht");
        three.setAddress("Driestraat");
        three.setPostalCode("1234DR");
        three.setResidence("Driestad");
        three.setActive(false);

        customers.add(one);
        customers.add(two);
        customers.add(three);
        return customers;
        //return cModel.getCustomers();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/customers/{id}")
    Customer getCustomer(@PathVariable String id){
        Customer one = new Customer();
        one.setId(101);
        one.setFirstName("Een");
        one.setLastName("Eendart");
        one.setAddress("Eenstraat");
        one.setPostalCode("1234EN");
        one.setResidence("EenStad");
        one.setActive(true);

        return one;
        //return cModel.getCustomer(id);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/tasks")
    List<GeneralTasks> getTasks(){

        return tModel.getTasks();
    }


}

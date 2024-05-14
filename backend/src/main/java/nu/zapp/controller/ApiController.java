//package nu.zapp.controller;
//
//import nu.zapp.ExceptionHandler.CustomerExceptionIdNum;
//import nu.zapp.ExceptionHandler.TaskExceptionIdNum;
//import nu.zapp.entities.Appointment;
//import nu.zapp.entities.Customer;
//import nu.zapp.entities.GeneralTasks;
//import nu.zapp.models.AppointmentModel;
//import nu.zapp.models.CustomerModel;
//import nu.zapp.models.EmployeeModel;
//import nu.zapp.models.TaskModel;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//
//@SpringBootApplication
//@RestController
//public class ApiController {
//    @Autowired
//    private AppointmentModel aModel;
//    @Autowired
//    private TaskModel tModel;
//    @Autowired
//    private CustomerModel cModel;
//
//
//    @GetMapping("/employees/{id}/appointments")
//    List<Appointment> getAppointments(@PathVariable int id, @RequestParam LocalDate date) {
//        return aModel.getAppointments(id, date);
//    }
//
//    @GetMapping("/appointment/{id}")
//    Appointment getAppointmentDetails(@PathVariable int id){
//        return aModel.getAppointmentDetails(id);
//    }
//
//
//    @CrossOrigin()
//    @GetMapping("/customers")
//    List<Customer> getCustomers(){
//        return cModel.getCustomers();
//    }
//
//    @CrossOrigin()
//    @GetMapping("/customers/{id}")
//    Customer getCustomer(@PathVariable int id){
//        Customer customer =  cModel.getCustomer(id);
//        if (customer == null){
//            throw new CustomerExceptionIdNum(id);
//        }
//        return customer;
//    }
//
//    @CrossOrigin()
//    @PostMapping("/customers")
//    Customer postCustomer(@RequestBody Customer newCustomer){
//        return cModel.createCustomer(newCustomer);
//    }
//
//    @CrossOrigin()
//    @PutMapping("/customers/{id}")
//    void putCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer){
//        if (id != updatedCustomer.getId()) {
//            throw new CustomerExceptionIdNum(id);
//        }
//        cModel.updateCustomer(updatedCustomer);
//    }
//
//
//    @CrossOrigin()
//    @GetMapping("/tasks")
//    List<GeneralTasks> getTasks(){
//
//        return tModel.getTasks();
//    }
//
//    @CrossOrigin()
//    @PostMapping("/tasks")
//    GeneralTasks postTask (@RequestBody GeneralTasks newTask){
//        return tModel.createTasks(newTask);
//    }
//
//    @CrossOrigin()
//    @PutMapping("/tasks/{id}")
//    void putTask(@PathVariable int id, @RequestBody GeneralTasks updatedTask){
//        if (id != updatedTask.getId()) {
//            throw new TaskExceptionIdNum(id);
//        }
//        tModel.updateTask(updatedTask);
//    }
//}

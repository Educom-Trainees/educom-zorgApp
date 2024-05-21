package nu.zapp;

import nu.zapp.entities.*;
import nu.zapp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TestDbFill {

    @Autowired
    private EmployeeModel eModel;

    @Autowired
    private CustomerModel cModel;

    @Autowired
    private TaskModel tModel;

    @Autowired
    private AppointmentModel aModel;

    @Autowired
    private EmployeeToAppointmentModel eTaModel;

    public void fillDb(){
        eModel.createEmployee(makeEmployeeOne());
        eModel.createEmployee(makeEmployeeTwo());
        cModel.createCustomer(makeCustomerOne());
        cModel.createCustomer(makeCustomerTwo());
        cModel.createCustomer(makeCustomerThree());
        tModel.createTasks(makeTaskOne());
        tModel.createTasks(makeTaskTwo());
        aModel.createAppointment(makeAppointmentOne(1));
        aModel.createAppointment(makeAppointmentTwo(2));
        eTaModel.createEtA(makeETA(1, 1));
    }

    private static Employee makeEmployeeOne(){
        Employee one=new Employee();
        //one.setId(101);
        one.setUsername("Een");
        one.setFirstName("Eendrecht");
        one.setLastName("Een");
        one.setRole("Employee");
        one.setPassword("Placeholder");
        one.setAddress("Een");
        one.setPostalcode("1234En");
        one.setResidence("EenStad");
        return one;
    }

    private static Employee makeEmployeeTwo(){
        Employee two=new Employee();
        //two.setId(102);
        two.setUsername("Twee");
        two.setFirstName("Twee");
        two.setLastName("Twee");
        two.setRole("Employee");
        two.setPassword("Placeholder");
        two.setAddress("Twee");
        two.setPostalcode("1234TW");
        two.setResidence("TweeStad");
        return two;
    }

    private static Customer makeCustomerOne(){
        Customer one = new Customer();
        //one.setId(101);
        one.setFirstName("Een");
        one.setLastName("Eendart");
        one.setAddress("Eenstraat");
        one.setPostalcode("1234EN");
        one.setResidence("EenStad");
        one.setActive(true);
        return one;
    }

    private static Customer makeCustomerTwo(){
        Customer two = new Customer();
        //two.setId(102);
        two.setFirstName("Twee");
        two.setLastName("Tweedart");
        two.setAddress("Tweestraat");
        two.setPostalcode("1234TW");
        two.setResidence("TweeStad");
        two.setActive(true);
        return two;
    }

    private static Customer makeCustomerThree(){
        Customer three = new Customer();
        //three.setId(103);
        three.setFirstName("Drie");
        three.setLastName("Driedrecht");
        three.setAddress("Driestraat");
        three.setPostalcode("1234DR");
        three.setResidence("Driestad");
        three.setActive(false);
        return three;
    }

    private static Generaltasks makeTaskOne(){
        Generaltasks one = new Generaltasks();
        //one.setId(101);
        one.setTask("Opstaan uit bed");
        one.setActive(true);
        return one;
    }

    private static Generaltasks makeTaskTwo(){
        Generaltasks two = new Generaltasks();
        //two.setId(102);
        two.setTask("Naar bed brengen");
        two.setActive(true);
        return two;
    }

    private Appointment makeAppointmentOne(int id){
        Appointment one = new Appointment();
        Customer customer = cModel.findById(id);
        one.setCustomer(customer);
        one.setDate(Date.valueOf(LocalDate.of(2024, 5, 24)).toLocalDate());
        one.setStartTime(LocalTime.of(16, 0));
        one.setEndTime(LocalTime.of(17, 0));
        return one;
    }

    private Appointment makeAppointmentTwo(int id){
        Appointment two = new Appointment();
        Customer customer = cModel.findById(id);
        two.setCustomer(customer);
        two.setStartTime(LocalTime.of(16, 0));
        two.setEndTime(LocalTime.of(17, 0));
        two.setDate(Date.valueOf(LocalDate.of(2024, 5, 22)).toLocalDate());
        return two;
    }

    private EmployeeToAppointment makeETA(int employeeId, int AppointmentId){
        EmployeeToAppointment one = new EmployeeToAppointment();
        Appointment appointment = aModel.findById(AppointmentId);
        Employee employee = eModel.findById(employeeId);
        one.setAppointment(appointment);
        one.setEmployee(employee);
        return one;
    }

}
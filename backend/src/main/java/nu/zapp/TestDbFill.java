package nu.zapp;

import nu.zapp.entities.*;
import nu.zapp.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    private CustomerTaskModel ctModel;



    public void fillDb(){
        eModel.createEmployee(makeEmployeeOne());
        eModel.createEmployee(makeEmployeeTwo());
        cModel.createCustomer(makeCustomerOne());
        cModel.createCustomer(makeCustomerTwo());
        cModel.createCustomer(makeCustomerThree());
        makeTasks();
        makeAppointments();
        ctModel.createTasks(makeCustomerTask(1));
        ctModel.createTasks(makeCustomerTaskTwo(1));
    }

    private void makeTasks(){
        List<String> tasks = Arrays.asList(
                "opstaan uit bed",
                "naar bed brengen",
                "hulp bij douchen",
                "hulp bij aankleden",
                "hulp bij uitkleden",
                "hulp bij eten of drinken",
                "medische taken",
                "medicatie geven",
                "toezicht op nemen medicatie",
                "verzorging van een wond",
                "stomazorg",
                "injecties geven",
                "insuline injecteren",
                "hulp bezoek huisarts",
                "oefenen zinnen spreken (logopedie)",
                "hulp bij de administratie",
                "leren om het huishouden zelf te doen",
                "samen een maaltijd bereiden",
                "samen werken aan meer sociale contacten",
                "wandeling maken",
                "(duo) fietsen",
                "afwassen/vaatwasser inruimen",
                "schoonmaken",
                "ramen zemen",
                "stofzuigen",
                "dweilen",
                "opruimen",
                "koken",
                "de was doen",
                "strijken",
                "koffie zetten",
                "hond(en) uitlaten",
                "boodschap(pen) doen"
        );
        for (String task : tasks) {
            Generaltasks newTask = new Generaltasks();
            newTask.setTask(task);
            newTask.setActive(true);
            newTask.setDuration(randomPicker(Arrays.asList(0, 5, 10, 15, 30, 45, 60)));
            tModel.createTasks(newTask);
        }
    }

    private Integer randomPicker(List<Integer> elements){
            Random random = new Random();
            int randomIndex = random.nextInt(elements.size());
        return elements.get(randomIndex);
    }

    private Employee makeEmployeeOne(){
        Employee one=new Employee();
        one.setUsername("Spongebob");
        one.setFirstName("Bob");
        one.setLastName("Sponse");
        one.setRole("Employee");
        one.setPassword("Placeholder");
        one.setAddress("Ananas 1");
        one.setPostalcode("1234BB");
        one.setResidence("Bikinibroek");
        one.setWorkSchedule(makeWorkScheduleOne());
        return one;
    }

    private Employee makeEmployeeTwo(){
        Employee two=new Employee();
        two.setUsername("PatrickSter");
        two.setFirstName("Patrick");
        two.setLastName("Ster");
        two.setRole("Employee");
        two.setPassword("Placeholder");
        two.setAddress("Steen 1");
        two.setPostalcode("1234BB");
        two.setResidence("Bikinibroek");
        two.setWorkSchedule(null);
        return two;
    }

    private static Customer makeCustomerOne(){
        Customer one = new Customer();
        one.setFirstName("Matthew");
        one.setLastName("Mercer");
        one.setAddress("Dorei 1");
        one.setPostalcode("1234TD");
        one.setResidence("Taldorei");
        one.setActive(true);
        return one;
    }

    private static Customer makeCustomerTwo(){
        Customer two = new Customer();
        two.setFirstName("Laura");
        two.setLastName("Bailey");
        two.setAddress("Dorai 2");
        two.setPostalcode("1234TD");
        two.setResidence("Taldorei");
        two.setActive(true);
        return two;
    }

    private static Customer makeCustomerThree(){
        Customer three = new Customer();
        //three.setId(103);
        three.setFirstName("Liam");
        three.setLastName("O'Brian");
        three.setAddress("Mount 3");
        three.setPostalcode("1234WD");
        three.setResidence("Wildmount");
        three.setActive(false);
        return three;
    }

    private void makeAppointments(){
        LocalDate startDate1 = LocalDate.of(2024, 5, 24);
        LocalDate endDate1 = LocalDate.of(2024, 5, 29);
        for (LocalDate date = startDate1; !date.isAfter(endDate1); date = date.plusDays(1)) {
            aModel.createAppointment(makeAppointmentOne(1, date));
            aModel.createAppointment(makeAppointmentTwo(2, date));
        }
        LocalDate startDate2 = LocalDate.of(2024, 6, 1);
        LocalDate endDate2 = LocalDate.of(2024, 6, 30);
        for (LocalDate date = startDate2; !date.isAfter(endDate2); date = date.plusDays(1)) {
            aModel.createAppointment(makeAppointmentOne(1, date));
            aModel.createAppointment(makeAppointmentTwo(2, date));
        }

    }

    private Appointment makeAppointmentOne(int id, LocalDate date){
        Appointment one = new Appointment();
        Customer customer = cModel.findById(id);
        Employee employee = eModel.findById(id);
        one.setEmployee(employee);
        one.setCustomer(customer);
        one.setDate(date);
        one.setAppointmentTasks(makeAppointmentTasks());
        return one;
    }

    private Appointment makeAppointmentTwo(int id, LocalDate date){
        Appointment two = new Appointment();
        Customer customer = cModel.findById(id);
        two.setCustomer(customer);
        two.setDate(date);
        two.setAppointmentTasks(makeAppointmentTasks());
        return two;
    }

    private List<AppointmentTasks> makeAppointmentTasks(){
        AppointmentTasks taskOne = new AppointmentTasks();
        AppointmentTasks taskTwo = new AppointmentTasks();
        taskOne.setTask("Koffie");
        taskTwo.setTask("Medicatie");
        taskTwo.setDuration(10);
        return List.of(taskOne, taskTwo);
    }

    private CustomerTasks makeCustomerTask(int id) {
        CustomerTasks one = new CustomerTasks();
        one.setCustomer(cModel.findById(id));
        one.setTask("Koffie");
        one.setAdditionalInfo("Twee klontjes suiker");
        return one;
    }

    private CustomerTasks makeCustomerTaskTwo(int id) {
        CustomerTasks two = new CustomerTasks();
        two.setCustomer(cModel.findById(id));
        two.setTask("Koffie (s'avonds)");
        two.setAdditionalInfo("Een klontje suiken");
        two.setStartTime(LocalTime.of(16, 0));
        two.setEndTime(LocalTime.of(17, 0));
        two.setDuration(5);
        return two;
    }

    private List<WorkSchedule> makeWorkScheduleOne(){
        WorkSchedule one = new WorkSchedule();
        one.setDay("Monday");
        one.setStart_shift(LocalTime.of(10, 0));
        one.setEnd_shift(LocalTime.of(16, 0));
        WorkSchedule two = new WorkSchedule();
        two.setDay("Wednesday");
        two.setStart_shift(LocalTime.of(9, 0));
        two.setEnd_shift(LocalTime.of(17, 30));
        return List.of(one, two);
    }
}
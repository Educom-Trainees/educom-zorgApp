//package nu.zapp.controller;
//
//
//import nu.zapp.entities.Appointment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@SpringBootApplication
//@RestController
//public class CalenderController {
//    @Autowired
//    private AppointmentRepository aRepository;
//
//    @GetMapping("/calender")
//    List<Appointment> getCalender() {
//        return aRepository.getCalender();
//    }
//    /**
//     * Get all appointsments of a certain employee
//     * @param id the id of the employee
//     * @param day the date of the employee
//     */
//}

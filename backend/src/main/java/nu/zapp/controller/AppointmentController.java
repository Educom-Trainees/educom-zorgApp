package nu.zapp.controller;


import nu.zapp.entities.Appointment;
import nu.zapp.entities.Customer;
import nu.zapp.models.AppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    AppointmentModel aModel;
    @CrossOrigin()
    @GetMapping("")
    List<Appointment> getAppointments(){
        return aModel.findAll();
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    Appointment getAppointment(@PathVariable int id) { return aModel.findById(id); }

    @CrossOrigin()
    @GetMapping("/employee/{id}/{date}")
    List<Appointment> getAppointmentsEmployee(@PathVariable int id, @PathVariable LocalDate date) {
        // date uses a year-month-day input xxxx-xx-xx
        return aModel.findEmployeeAppointments(id, date); }

}

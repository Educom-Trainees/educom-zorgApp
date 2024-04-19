package nu.zapp.controller;

import nu.zapp.entities.Appointment;
import nu.zapp.models.AppointmentModel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ApiController {
    private AppointmentModel aModel;

    @GetMapping("/calender")
    List<Appointment> getCalender() {
        return aModel.getCalender();
    }

    //To get the current day planning for an employee
    @GetMapping("/appointment/{employee}")
    List<Appointment> getAppointments(@PathVariable String employee) {
        return aModel.getAppointments(employee);
    }

    @GetMapping("/appointment/{appointmentId}")
    Appointment getAppointmentDetails(@PathVariable String appointmentId){
        return aModel.getAppointmentDetails(appointmentId);
    }
}

package nu.zapp.controller;


import nu.zapp.entities.Appointment;
import nu.zapp.entities.Customer;
import nu.zapp.models.AppointmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

package nu.zapp.controller;


import nu.zapp.DTO.AppointmentDTO;
import nu.zapp.entities.Appointment;
import nu.zapp.entities.Customer;
import nu.zapp.mappers.AppointmentSourceDestinationMapper;
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

    @Autowired
    private AppointmentSourceDestinationMapper mapper;
    @CrossOrigin()
    @GetMapping("")
    List<AppointmentDTO> getAppointments(){
        return mapper.sourceToDestination(aModel.findAll());
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    AppointmentDTO getAppointment(@PathVariable int id) {
        return mapper.sourceToDestination(aModel.findById(id)); }

    @CrossOrigin()
    @GetMapping("/employee/{id}/{date}")
    List<AppointmentDTO> getAppointmentsEmployee(@PathVariable int id, @PathVariable LocalDate date) {
        // date uses a year-month-day input xxxx-xx-xx
        return mapper.sourceToDestination(aModel.findEmployeeAppointments(id, date));
    }

}

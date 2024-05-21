package nu.zapp.controller;


import nu.zapp.DTO.AppointmentDTO;
import nu.zapp.mappers.AppointmentMapper;
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
    private AppointmentMapper mapper;
    @CrossOrigin()
    @GetMapping("")
    List<AppointmentDTO> getAppointments(){
        return mapper.sourceToDestination(aModel.findAll());
    }

    @CrossOrigin()
    @GetMapping("/by-date/{date}")
    List<AppointmentDTO> getAppointmentsWeek(@PathVariable LocalDate date){
        return mapper.sourceToDestination(aModel.findWeek(date));
    }

    @CrossOrigin()
    @GetMapping("/by-id/{id}")
    AppointmentDTO getAppointment(@PathVariable int id) {
        // this will miss data such as the employee and the tasks
        return mapper.sourceToDestination(aModel.findById(id)); }

    @CrossOrigin()
    @GetMapping("/employee/{id}/{date}")
    List<AppointmentDTO> getAppointmentsEmployee(@PathVariable int id, @PathVariable LocalDate date) {
        // date uses a year-month-day input xxxx-xx-xx
        return mapper.sourceToDestination(aModel.findEmployeeAppointments(id, date));
    }

}

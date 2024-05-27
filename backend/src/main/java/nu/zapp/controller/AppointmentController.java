package nu.zapp.controller;


import nu.zapp.DTO.AppointmentDetailDTO;
import nu.zapp.entities.Appointment;
import nu.zapp.mappers.AppointmentDetailMapper;
import nu.zapp.mappers.AppointmentMapper;
import nu.zapp.models.AppointmentModel;
import nu.zapp.models.CustomerModel;
import nu.zapp.models.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@SpringBootApplication
@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentModel aModel;
    @Autowired
    private CustomerModel cModel;
    @Autowired
    private EmployeeModel eModel;
    @Autowired
    private AppointmentMapper mapper;
    @Autowired
    private AppointmentDetailMapper dMapper;


    @CrossOrigin()
    @GetMapping("")
    Object getAppointments(@RequestParam(value = "employee_id", required = false) Integer employee_id,
                                                @RequestParam(value = "date", required = false) LocalDate date,
                                                @RequestParam(value = "count", required = false, defaultValue = "0") int count,
                                                @RequestParam(value = "id", required = false) Integer id) {
        if (id != null){
            return dMapper.sourceToDestination(aModel.findById(id));
        }
         else if (employee_id != null) {
            return mapper.sourceToDestination(aModel.findEmployeeAppointments(employee_id, date, count));
        } else if (date != null) {
            return mapper.sourceToDestination(aModel.findByDates(date, count));
        } else {
            return mapper.sourceToDestination(aModel.findAll());
        }
    }

    @CrossOrigin()
    @PostMapping("")
    AppointmentDetailDTO createAppointment(@RequestBody AppointmentDetailDTO newAppointment){
        //TODO additional DTO to convert appointmentTasks
        Appointment appointment = dMapper.destinationToSource(newAppointment, cModel, eModel);
        return dMapper.sourceToDestination( aModel.createAppointment(appointment));
    }

}

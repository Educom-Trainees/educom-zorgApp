package nu.zapp.models;

import nu.zapp.DTO.AppointmentDTO;
import nu.zapp.entities.Appointment;
import nu.zapp.mappers.AppointmentSourceDestinationMapper;
import nu.zapp.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class AppointmentModel {

    @Autowired
    private AppointmentRepository aRepository;
    @Autowired
    private AppointmentSourceDestinationMapper mapper;

    public List<AppointmentDTO> findAll() {
        List<Appointment> appointments = aRepository.findAll();
        return mapper.sourceToDestination(appointments);
    }

    public Appointment findById(int id){
        return aRepository.findById(id);
    }

    public Appointment createAppointment(Appointment newAppointment){
        return aRepository.save(newAppointment);
    }

    public List<AppointmentDTO> findEmployeeAppointments(int id, LocalDate date) {
        List<Appointment> appointments = aRepository.findEmployeeAppointments(id, date);
        return mapper.sourceToDestination(appointments);
    }
}

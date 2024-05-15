package nu.zapp.models;


import nu.zapp.entities.EmployeeToAppointment;
import nu.zapp.repositories.EmployeeToAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeToAppointmentModel {
    @Autowired
    EmployeeToAppointmentRepository eTaRepository;

    public EmployeeToAppointment createEtA(EmployeeToAppointment employeeToAppointment) {
        return eTaRepository.save(employeeToAppointment);
    }
}

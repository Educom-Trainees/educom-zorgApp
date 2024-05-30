package nu.zapp.DTO;

import nu.zapp.entities.WorkSchedule;

import java.util.List;

public class EmployeeDTO extends PersonDTO {

    private List<WorkSchedule> workSchedule;

    public EmployeeDTO(int id, String name, String address, Boolean active, List<WorkSchedule> workSchedule) {
        super(id, name, address, active);
        this.workSchedule = workSchedule;
    }

    public List<WorkSchedule> getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(List<WorkSchedule> workSchedule) {
        this.workSchedule = workSchedule;
    }
}

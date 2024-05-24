package nu.zapp.DTO;

import nu.zapp.entities.WorkSchedule;

import java.util.List;

public class EmployeeDetailDTO extends EmployeeDTO{
    private String last_name;
    private String postalcode;
    private String residence;
    private List<WorkSchedule> schedule;

    public EmployeeDetailDTO(int id, String name, String address, Boolean active,
                             String last_name, String postalcode, String residence, List<WorkSchedule> schedule) {
        super(id, name, address, active);
        this.last_name = last_name;
        this.postalcode = postalcode;
        this.residence = residence;
        this.schedule = schedule;
    }
}

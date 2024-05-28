package nu.zapp.DTO;

import nu.zapp.entities.WorkSchedule;

import java.util.List;

public class EmployeeDetailDTO extends EmployeeDTO{
    private String lastName;
    private String postalcode;
    private String residence;
    private List<WorkSchedule> schedule;

    public EmployeeDetailDTO(int id, String name, String address, Boolean active,
                             String lastName, String postalcode, String residence, List<WorkSchedule> schedule) {
        super(id, name, address, active);
        this.lastName = lastName;
        this.postalcode = postalcode;
        this.residence = residence;
        this.schedule = schedule;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public List<WorkSchedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<WorkSchedule> schedule) {
        this.schedule = schedule;
    }
}

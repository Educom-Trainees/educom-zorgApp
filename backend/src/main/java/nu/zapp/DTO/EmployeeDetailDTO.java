package nu.zapp.DTO;

import nu.zapp.entities.WorkSchedule;

import java.util.List;

public class EmployeeDetailDTO extends EmployeeDTO{
    private String lastName;
    private String postalcode;
    private String residence;

    public EmployeeDetailDTO(int id, String name, String address, Boolean active, List<WorkSchedule> workSchedule,
                             String lastName, String postalcode, String residence) {
        super(id, name, address, active, workSchedule);
        this.lastName = lastName;
        this.postalcode = postalcode;
        this.residence = residence;
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



}

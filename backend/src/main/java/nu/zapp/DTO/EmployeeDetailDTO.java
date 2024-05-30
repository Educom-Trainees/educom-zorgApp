package nu.zapp.DTO;

import nu.zapp.entities.WorkSchedule;

import java.util.List;

public class EmployeeDetailDTO extends EmployeeDTO{
    private String lastName;
    private String postalcode;
    private String residence;
    private String username;
    private String role;

    public EmployeeDetailDTO(int id, String name, String address, Boolean active,
                             List<WorkSchedule> workSchedule, String lastName,
                             String postalcode, String residence, String username, String role) {
        super(id, name, address, active, workSchedule);
        this.lastName = lastName;
        this.postalcode = postalcode;
        this.residence = residence;
        this.username = username;
        this.role = role;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

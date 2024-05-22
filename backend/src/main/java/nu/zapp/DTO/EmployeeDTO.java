package nu.zapp.DTO;

import java.util.List;

public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String residence;

    private List<String> days;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int id, String firstName, String lastName, String address, String postalCode, String residence) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.residence = residence;
    }


}

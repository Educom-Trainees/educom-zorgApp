package nu.zapp.DTO;

import java.util.List;

public class EmployeeDTO extends PersonDTO {


    public EmployeeDTO(int id, String name, String address, Boolean active) {
        super(id, name, address, active);
    }
}

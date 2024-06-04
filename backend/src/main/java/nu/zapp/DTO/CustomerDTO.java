package nu.zapp.DTO;


import java.time.LocalDate;

public class CustomerDTO extends PersonDTO {

    private LocalDate inactivationDate;

    public CustomerDTO(int id, String name, String address, LocalDate inactivationDate) {
        super(id, name, address);
        this.inactivationDate = inactivationDate;
    }

    public LocalDate getInactivationDate() {
        return inactivationDate;
    }

    public void setInactivationDate(LocalDate inactivationDate) {
        this.inactivationDate = inactivationDate;
    }
}

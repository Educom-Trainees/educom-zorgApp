package nu.zapp.DTO;

import nu.zapp.entities.CustomerTasks;
import java.util.List;

public class CustomerDetailDTO extends CustomerDTO{
    private String lastName;
    private String postalcode;
    private String residence;
    private List<CustomerTasks> tasks;

    public CustomerDetailDTO(int id, String name, String address, Boolean active, String lastName,
                             String postalcode, String residence, List<CustomerTasks> tasks) {
        super(id, name, address, active);
        this.lastName = lastName;
        this.postalcode = postalcode;
        this.residence = residence;
        this.tasks = tasks;
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

    public List<CustomerTasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<CustomerTasks> tasks) {
        this.tasks = tasks;
    }
}

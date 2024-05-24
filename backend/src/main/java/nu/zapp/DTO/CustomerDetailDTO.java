package nu.zapp.DTO;

import nu.zapp.entities.CustomerTasks;
import java.util.List;

public class CustomerDetailDTO extends CustomerDTO{
    private String last_name;
    private String postalcode;
    private String residence;
    private List<CustomerTasks> tasks;

    public CustomerDetailDTO(int id, String name, String address, Boolean active, String last_name,
                             String postalcode, String residence, List<CustomerTasks> tasks) {
        super(id, name, address, active);
        this.last_name = last_name;
        this.postalcode = postalcode;
        this.residence = residence;
        this.tasks = tasks;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

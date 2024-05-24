package nu.zapp.DTO;

public class PersonDTO {
    private int id;
    private String name;
    private String address;
    private boolean active;

    public PersonDTO(int id, String name, String address, Boolean active) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

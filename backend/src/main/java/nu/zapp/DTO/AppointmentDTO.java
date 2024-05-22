package nu.zapp.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
    private int id;
    private LocalDate date;
    private LocalTime start_time;
    private LocalTime end_time;
    private LocalTime register_time;
    private LocalTime logout_time;
    private String name;
    private String location;

    public AppointmentDTO() {
    }

    public AppointmentDTO(int id, LocalDate date, LocalTime start_time, LocalTime end_time,
                          LocalTime register_time, LocalTime logout_time, String name, String location) {
        this.id = id;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.register_time = register_time;
        this.logout_time = logout_time;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalTime start_time) {
        this.start_time = start_time;
    }

    public LocalTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalTime end_time) {
        this.end_time = end_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalTime getRegister_time() {
        return register_time;
    }

    public void setRegister_time(LocalTime register_time) {
        this.register_time = register_time;
    }

    public LocalTime getLogout_time() {
        return logout_time;
    }

    public void setLogout_time(LocalTime logout_time) {
        this.logout_time = logout_time;
    }
}

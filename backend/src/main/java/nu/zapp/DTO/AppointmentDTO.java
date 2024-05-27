package nu.zapp.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDTO {
    private int id;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime registerTime;
    private LocalTime logoutTime;
    private String name;
    private String location;

    public AppointmentDTO() {
    }

    public AppointmentDTO(int id, LocalDate date, LocalTime startTime, LocalTime endTime,
                          LocalTime registerTime, LocalTime logoutTime,
                          String name, String location) {
        this.id = id;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.registerTime = registerTime;
        this.logoutTime = logoutTime;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalTime getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(LocalTime registerTime) {
        this.registerTime = registerTime;
    }

    public LocalTime getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(LocalTime logoutTime) {
        this.logoutTime = logoutTime;
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
}

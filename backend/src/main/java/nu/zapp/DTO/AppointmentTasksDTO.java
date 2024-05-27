package nu.zapp.DTO;

import java.time.LocalTime;

public class AppointmentTasksDTO {
    private String task;
    private String additional_info;
    private boolean completed;
    private String note_employee;
    private boolean note_read;
    private LocalTime start_time;
    private LocalTime end_time;

    public AppointmentTasksDTO(String task, String additional_info,
                               boolean completed, String note_employee, boolean note_read,
                               LocalTime start_time, LocalTime end_time) {
        this.task = task;
        this.additional_info = additional_info;
        this.completed = completed;
        this.note_employee = note_employee;
        this.note_read = note_read;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getNote_employee() {
        return note_employee;
    }

    public void setNote_employee(String note_employee) {
        this.note_employee = note_employee;
    }

    public boolean isNote_read() {
        return note_read;
    }

    public void setNote_read(boolean note_read) {
        this.note_read = note_read;
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
}

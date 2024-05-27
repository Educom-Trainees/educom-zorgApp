package nu.zapp.DTO;

import java.time.LocalTime;

public class AppointmentTasksDTO {
    private String task;
    private String additionalInfo;
    private boolean completed;
    private String noteEmployee;
    private boolean noteRead;
    private LocalTime startTime;
    private LocalTime endTime;

    public AppointmentTasksDTO(String task, String additionalInfo, boolean completed,
                               String noteEmployee, boolean noteRead,
                               LocalTime startTime, LocalTime endTime) {
        this.task = task;
        this.additionalInfo = additionalInfo;
        this.completed = completed;
        this.noteEmployee = noteEmployee;
        this.noteRead = noteRead;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getNoteEmployee() {
        return noteEmployee;
    }

    public void setNoteEmployee(String noteEmployee) {
        this.noteEmployee = noteEmployee;
    }

    public boolean isNoteRead() {
        return noteRead;
    }

    public void setNoteRead(boolean noteRead) {
        this.noteRead = noteRead;
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
}

package nu.zapp.entities;

import jakarta.persistence.*;

@Entity
public class AppointmentTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    private int appointmentId;

    private String task;

    private String note;

    private boolean completed;

    private String noteEmployee;

    public AppointmentTasks() {
    }

    public AppointmentTasks(int id, int appointmentId, String task, String note, boolean completed, String noteEmployee) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.task = task;
        this.note = note;
        this.completed = completed;
        this.noteEmployee = noteEmployee;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
}

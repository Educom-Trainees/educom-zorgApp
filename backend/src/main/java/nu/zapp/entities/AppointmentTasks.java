package nu.zapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class AppointmentTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id")
    @JsonBackReference
    private Appointment appointment;

    private String task;

    private String note;

    private boolean completed;

    private String noteEmployee;

    private boolean noteRead;

    public AppointmentTasks() {
    }

    public AppointmentTasks(int id, Appointment appointment, String task, String note, boolean completed, String noteEmployee, boolean noteRead) {
        this.id = id;
        this.appointment = appointment;
        this.task = task;
        this.note = note;
        this.completed = completed;
        this.noteEmployee = noteEmployee;
        this.noteRead = noteRead;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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

    public boolean isNoteRead() {
        return noteRead;
    }

    public void setNoteRead(boolean noteRead) {
        this.noteRead = noteRead;
    }
}

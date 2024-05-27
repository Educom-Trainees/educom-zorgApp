package nu.zapp.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class AppointmentTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    @JsonBackReference
    private Appointment appointment;

    private String task;

    @Column()
    private LocalTime startTime;

    @Column()
    private LocalTime endTime;

    private String additionalInfo;

    private boolean completed;

    private String noteEmployee;

    private boolean noteRead;

    public AppointmentTasks() {
    }

    public AppointmentTasks(int id, Appointment appointment, String task, LocalTime startTime, LocalTime endTime,
                            String additionalInfo, boolean completed, String noteEmployee, boolean noteRead) {
        this.id = id;
        this.appointment = appointment;
        this.task = task;
        this.startTime = startTime;
        this.endTime = endTime;
        this.additionalInfo = additionalInfo;
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
}

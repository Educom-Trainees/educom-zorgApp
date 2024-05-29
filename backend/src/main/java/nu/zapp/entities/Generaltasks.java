package nu.zapp.entities;


import jakarta.persistence.*;

import java.time.LocalTime;


@Entity
public class Generaltasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String task;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private boolean active;

    @Column()
    private LocalTime startTime;

    @Column()
    private LocalTime endTime;

    private int duration;

    public Generaltasks() {
    }

    public Generaltasks(int id, String task, boolean active, LocalTime startTime, LocalTime endTime, int duration) {
        this.id = id;
        this.task = task;
        this.active = active;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

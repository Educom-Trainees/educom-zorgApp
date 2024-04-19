package nu.zapp.entities;

import jakarta.persistence.*;

@Entity
public class CustomerSetTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    private int customerId;

    private String task;
    private String note;

    public CustomerSetTasks() {
    }

    public CustomerSetTasks(int id, int customerId, String task, String note) {
        this.id = id;
        this.customerId = customerId;
        this.task = task;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
}

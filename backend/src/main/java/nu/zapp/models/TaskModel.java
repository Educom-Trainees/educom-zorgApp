package nu.zapp.models;

import nu.zapp.entities.GeneralTasks;
import nu.zapp.services.Crud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskModel {

    private final Crud crud;

    @Autowired
    public TaskModel(Crud crud) {
        this.crud = crud;
    }
    public List<GeneralTasks> getTasks() {
        String queryString = "select a from GeneralTasks a";
        return crud.readMultipleRows(GeneralTasks.class, queryString, "Error reading General Task List");
    }

    public GeneralTasks createTasks(GeneralTasks newTask){
        GeneralTasks createdTask = crud.createRow(newTask, "Taak kon niet gemaakt worden");
        return createdTask;
    }

    public void updateTask(GeneralTasks updatedTask) {
        crud.updateRow(updatedTask, "Taak kon niet worden bijgewerkt");
    }
}

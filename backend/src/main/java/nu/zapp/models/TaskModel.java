package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.entities.Generaltasks;
import nu.zapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TaskModel {

    @Autowired
    private TaskRepository tRepository;

    public List<Generaltasks> findAll(){
        return tRepository.findAll();
    }

    public Generaltasks createTasks(Generaltasks newTask){
        //First letter of a task should be capitalised for consistency
        String task = newTask.getTask();
        String taskCap = task.substring(0, 1).toUpperCase() + task.substring(1);
        newTask.setTask(taskCap);
        if (tRepository.findByTask(newTask.getTask()) != null){
            throw new ExceptionItemExists("taak");
        }
        return tRepository.save(newTask);
    }

}

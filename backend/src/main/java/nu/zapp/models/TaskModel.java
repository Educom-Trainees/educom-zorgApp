package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Employee;
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
        newTask.setId(0);
        newTask.setTask(taskCap(newTask));
        newTask.setActive(true);
        if (tRepository.findByTask(newTask.getTask()) != null){
            throw new ExceptionItemExists("taak");
        }
        return tRepository.save(newTask);
    }

    public Generaltasks updateTasks(Generaltasks updateTask) {
        updateTask.setTask(taskCap(updateTask));
        return tRepository.save(updateTask);
    }

    private String taskCap(Generaltasks task){
        String taskName = task.getTask();
        return(taskName.substring(0, 1).toUpperCase() + taskName.substring(1));
    }

    public Generaltasks findById(int id) {
        Generaltasks task = tRepository.findById(id);
        if (task == null){
            throw new ExceptionNumId(id, "taak");
        }
        return task;
    }
}

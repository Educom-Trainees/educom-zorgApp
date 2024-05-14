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
        if (tRepository.findByTask(newTask.getTask()) != null){
            throw new ExceptionItemExists("taak");
        }
        return tRepository.save(newTask);
    }

}

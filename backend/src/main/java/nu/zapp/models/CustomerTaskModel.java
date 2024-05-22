package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.CustomerSetTasks;
import nu.zapp.entities.Generaltasks;
import nu.zapp.repositories.CustomerTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerTaskModel {
    @Autowired
    private CustomerTaskRepository cRepository;

    public List<CustomerSetTasks> findAll() {return cRepository.findAll(); }

    public CustomerSetTasks createTasks(CustomerSetTasks newTask){
        //First letter of a task should be capitalised for consistency
        newTask.setId(0);
        newTask.setTask(taskCap(newTask));
        newTask.setActive(true);
        if (cRepository.findByTask(newTask.getTask()) != null){
            throw new ExceptionItemExists("taak");
        }
        return cRepository.save(newTask);
    }

    public CustomerSetTasks updateTasks(CustomerSetTasks updateTask) {
        updateTask.setTask(taskCap(updateTask));
        return cRepository.save(updateTask);
    }

    private String taskCap(CustomerSetTasks task){
        String taskName = task.getTask();
        return(taskName.substring(0, 1).toUpperCase() + taskName.substring(1));
    }

    public CustomerSetTasks findById(int id) {
        CustomerSetTasks task = cRepository.findById(id);
        if (task == null){
            throw new ExceptionNumId(id, "taak");
        }
        return task;
    }
}

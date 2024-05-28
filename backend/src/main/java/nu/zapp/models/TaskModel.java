package nu.zapp.models;

import nu.zapp.ExceptionHandler.ExceptionInvalidInput;
import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.ExceptionHandler.ExceptionMissingInput;
import nu.zapp.ExceptionHandler.ExceptionNumId;
import nu.zapp.entities.Generaltasks;
import nu.zapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class TaskModel {

    @Autowired
    private TaskRepository tRepository;

    public List<Generaltasks> findAll(){
        return tRepository.findAll();
    }

    public Generaltasks createTasks(Generaltasks newTask){
        newTask.setId(0);
        newTask.setTask(taskCap(newTask));
        newTask.setActive(true);
        if (tRepository.findByTask(newTask.getTask()) != null){
            throw new ExceptionItemExists("taak");
        }

        checkTimes(newTask.getStartTime(), newTask.getEndTime());

        return tRepository.save(newTask);
    }

    public Generaltasks updateTasks(Generaltasks updateTask) {
        updateTask.setTask(taskCap(updateTask));
        checkTimes(updateTask.getStartTime(), updateTask.getEndTime());
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

    private void checkTimes(LocalTime startTime, LocalTime endTime){
        if (startTime != null && endTime != null) {
            if (startTime.isAfter(endTime)) {
                throw new ExceptionInvalidInput("start tijd");
            }
        } else {
            if (startTime == null) {
                throw new ExceptionMissingInput("start tijd");
            } else {
                throw new ExceptionMissingInput("eind tijd");
            }
        }
    }

    public void deleteTask(int id) {
        Generaltasks toDeleteTask = tRepository.findById(id);
        tRepository.delete(toDeleteTask);
    }
}

package nu.zapp.controller;


import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.entities.Generaltasks;
import nu.zapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/tasks")
public class TasksController {
    @Autowired
    private TaskRepository tRepository;

    @CrossOrigin()
    @GetMapping("")
    List<Generaltasks> getTasks(){
        return (List<Generaltasks>) tRepository.findAll();
    }

    @CrossOrigin()
    @PostMapping("")
    Generaltasks postTasks(@RequestBody Generaltasks newTask){
        //First need to check if the task is already in the DB
        if (tRepository.findByTask(newTask.getTask()) != null){
                throw new ExceptionItemExists("taak");
        }
        return tRepository.save(newTask);
    }
}

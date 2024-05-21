package nu.zapp.controller;


import nu.zapp.ExceptionHandler.ExceptionItemExists;
import nu.zapp.entities.Generaltasks;
import nu.zapp.models.TaskModel;
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
    private TaskModel tModel;

    @CrossOrigin()
    @GetMapping("")
    List<Generaltasks> getTasks(){
        return tModel.findAll();
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    Generaltasks getTask(@PathVariable int id) { return tModel.findById(id);}

    @CrossOrigin()
    @PostMapping("")
    Generaltasks postTasks(@RequestBody Generaltasks newTask){
        return tModel.createTasks(newTask);
    }

    @CrossOrigin()
    @PutMapping ("")
    Generaltasks updateTasks(@RequestBody Generaltasks updateTask){
        return tModel.updateTasks(updateTask);
    }
}

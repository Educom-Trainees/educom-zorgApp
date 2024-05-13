package nu.zapp.controller;


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
        return tRepository.save(newTask);
    }
}

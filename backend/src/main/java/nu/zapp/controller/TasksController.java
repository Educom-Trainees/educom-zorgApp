package nu.zapp.controller;

import nu.zapp.entities.Generaltasks;
import nu.zapp.models.TaskModel;
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

    //Object is unspecified, depends on @RequestParams  (commented 2024-06-03)
    @CrossOrigin()
    @GetMapping("")
    Object getTasks(@RequestParam(value="id", required = false)Integer id){
        if (id != null){
            return tModel.findById(id);
        }
        return tModel.findAll();
    }

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

    @CrossOrigin()
    @DeleteMapping("")
    void deleteTask(@RequestBody int id){
        tModel.deleteTask(id);
    }
}

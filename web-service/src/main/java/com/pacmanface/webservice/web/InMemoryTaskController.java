package com.pacmanface.webservice.web;

import com.pacmanface.webservice.Task;
import com.pacmanface.webservice.data.TaskStorage;
import com.pacmanface.webservice.hateoas.TaskModel;
import com.pacmanface.webservice.hateoas.TaskModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/tasks", produces = "application/json")
public class InMemoryTaskController {

    @Autowired
    TaskStorage storage;

    @Autowired
    TaskModelAssembler assembler;

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Task task) {
       storage.saveTask(task);
    }

    @PatchMapping(path ="/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public TaskModel update(@PathVariable("id") Long id, @RequestBody Task patch) {
        return assembler.toModel(storage.updateTask(id,patch));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            storage.deleteTask(id);
        }catch (Exception e) {}
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CollectionModel<TaskModel> getTasks() {
       return assembler.toCollectionModel(storage.getTasks());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskModel getById(@PathVariable("id") Long id) {
        return assembler.toModel(storage.getTaskById(id));
    }
}


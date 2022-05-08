package com.pacmanface.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/notsorestfull", produces = "application/json")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping
    public Iterable<Task> getTasks() {
        PageRequest page = PageRequest.of(0,10, Sort.by("lastModDate").descending());
        return repository.findAll(page).getContent();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") Long id) {
        if(repository.findById(id).isPresent()) {
            return new ResponseEntity<Task>(repository.findById(id).get(), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
       return new ResponseEntity<Task>(repository.save(task),HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskById(@PathVariable("id") Long id) {
        try{
            repository.deleteById(id);
        }catch (EmptyResultDataAccessException e){}
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Task putTask(@PathVariable("id") Long id, @RequestBody Task task) {
        task.setId(id);
        return repository.save(task);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Task updateTask(@PathVariable("id") Long id, @RequestBody Task patch) {
        Task task = repository.findById(id).get();
        task.setName(patch.getName());
        task.setDescription(patch.getDescription());
        return repository.save(task);
    }
}

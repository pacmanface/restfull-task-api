package com.pacmanface.webservice;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/memory", produces = "application/json")
public class InMemoryTaskController {

    private final Map<Long, Task> taskMap = new ConcurrentHashMap<>();


    public void create(String name, String descriprtion) {
        Task task = new Task();
        task.setName(name);
        task.setDescription(descriprtion);
        taskMap.put(task.getId(), task);
    }

    public void update(Long id, String name, String description) {
        Task task = taskMap.get(id);
        task.setName(name);
        task.setDescription(description);
        task.setLastModDate(new Date());
        taskMap.put(task.getId(),task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        taskMap.remove(id);
    }

    @GetMapping
    public Iterable<Task> getTasksFromMap() {
       return taskMap.values();
    }

    @GetMapping("/{id}")
    public Task getById(@PathVariable("id") Long id) {
        return taskMap.get(id);
    }
}

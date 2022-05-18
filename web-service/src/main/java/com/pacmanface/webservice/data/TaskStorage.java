package com.pacmanface.webservice.data;

import com.pacmanface.webservice.Task;
import lombok.Getter;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TaskStorage {
    @Getter
    private final Map<Long, Task> taskStorage = new ConcurrentHashMap<>();

    public Task getTaskById(Long id) {
        return taskStorage.get(id);
    }

    public Iterable<Task> getTasks() {
        List<Task> taskList = new ArrayList<>();
        taskList.addAll(taskStorage.values());

        Collections.sort(taskList, new Comparator<Task>(){
            @Override
            public int compare(Task o1, Task o2) {
                return o2.getLastModDate().compareTo(o1.getLastModDate());
            }
        });

        return taskList;
    }

    public Task saveTask(Task task) {
        return taskStorage.put(task.getId(),task);
    }

    public Task updateTask(Long id, Task patch) {
        Task task = taskStorage.get(id);
        task.setName(patch.getName());
        task.setDescription(patch.getDescription());
        task.setLastModDate(new Date());
        return taskStorage.put(task.getId(),task);
    }

    public void deleteTask(Long id) {
        taskStorage.remove(id);
    }
}

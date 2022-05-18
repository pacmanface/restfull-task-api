package com.pacmanface.webservice.config;

import com.pacmanface.webservice.Task;
import com.pacmanface.webservice.data.TaskRepository;
import com.pacmanface.webservice.data.TaskStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TaskConfig {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStorage storage;

    @Bean
    public CommandLineRunner initTasks(){
        return args -> {
            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i < 20 ; i++) {
                Task task = new Task();
                task.setName("name for "+ task.getId());
                task.setDescription("description for "+task.getId());
                try {
                    Thread.sleep(400);
                }catch (Exception e) {}
                tasks.add(task);
                storage.saveTask(task);
            }
            taskRepository.saveAll(tasks);
        };
    }
}

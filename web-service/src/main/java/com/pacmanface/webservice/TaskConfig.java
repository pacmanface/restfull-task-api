package com.pacmanface.webservice;

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

    @Bean
    public CommandLineRunner initTasks(){
        return args -> {
            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i < 20 ; i++) {
                Task task = new Task();
                task.setName("name "+ task.getId());
                task.setDescription("description for "+task.getId());
                tasks.add(task);
            }
            taskRepository.saveAll(tasks);
        };
    }
}

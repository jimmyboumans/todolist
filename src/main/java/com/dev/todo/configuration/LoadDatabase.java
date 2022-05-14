package com.dev.todo.configuration;

import com.dev.todo.entity.Task;
import com.dev.todo.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TaskRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Task("Task 1", "Description 1", false)));
            log.info("Preloading " + repository.save(new Task("Task 2", "Description 2", true)));
            log.info("Preloading " + repository.save(new Task("Task 3", "Description 3", false)));
            log.info("Preloading " + repository.save(new Task("Task 4", "Description 4", true)));
            log.info("Preloading " + repository.save(new Task("Task 5", "Description 5", false)));
        };
    }
}

package com.dev.todo.controller;

import com.dev.todo.entity.Task;
import com.dev.todo.exception.TaskNotFoundException;
import com.dev.todo.repository.TaskRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TaskController {

    private final TaskRepository repository;

    TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/task")
    CollectionModel<EntityModel<Task>> findAllTasks() {
        List<EntityModel<Task>> tasks = repository.findAll().stream()
                .map(task -> EntityModel.of(task,
                        linkTo(methodOn(TaskController.class).findTaskById(task.getId())).withSelfRel(),
                        linkTo(methodOn(TaskController.class).findAllTasks()).withRel("tasks")))
                .collect(Collectors.toList());

        return CollectionModel.of(tasks, linkTo(methodOn(TaskController.class).findAllTasks()).withSelfRel());
    }

    @PostMapping("/task")
    Task saveNewTask(@RequestBody Task newTask) {
        return repository.save(newTask);
    }

    @GetMapping("/task/{id}")
    EntityModel<Task> findTaskById(@PathVariable Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return EntityModel.of(task, //
                linkTo(methodOn(TaskController.class).findTaskById(id)).withSelfRel(),
                linkTo(methodOn(TaskController.class).findAllTasks()).withRel("tasks"));
    }

    @PutMapping("/task/{id}")
    Task updateTask(@RequestBody Task updatedTask, @PathVariable Long id) {
        return repository.findById(id)
                .map(task -> {
                    task.setTitle(updatedTask.getTitle());
                    task.setDescription(updatedTask.getDescription());
                    task.setStatus(updatedTask.getStatus());
                    return repository.save(task);
                }).orElseGet(() -> {
                    updatedTask.setId(id);
                    return repository.save(updatedTask);
                });
    }

    @DeleteMapping("/task/{id}")
    void deleteTask(@PathVariable Long id) {
        repository.deleteById(id);
    }

}

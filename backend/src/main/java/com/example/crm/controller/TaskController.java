package com.example.crm.controller;

import com.example.crm.model.Task;
import com.example.crm.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updated) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(updated.getTitle());
        task.setDescription(updated.getDescription());
        task.setDueDate(updated.getDueDate());
        task.setPriority(updated.getPriority());
        task.setAssignedTo(updated.getAssignedTo());
        task.setStatus(updated.getStatus());

        return taskRepository.save(task);
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "Task deleted successfully";
    }
}
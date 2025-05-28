package com.gaurav.tasktracker.controller;


import com.gaurav.tasktracker.dto.TaskRequest;
import com.gaurav.tasktracker.entity.Task;
import com.gaurav.tasktracker.entity.User;
import com.gaurav.tasktracker.service.TaskService;
import com.gaurav.tasktracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")

public class TaskController {


    private final TaskService taskService;
    private final UserService userService;
    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

//    @PostMapping
//    public ResponseEntity<Task> createTask(@RequestBody Task task,@RequestParam Long userId ) {
//        Optional<User> optionalUser  = userService.getUserById(userId);
//        if( optionalUser.isPresent()){
//            task.setUser(optionalUser.get());
//            Task createdTask = taskService.createTask(task);
//            return ResponseEntity.ok(createdTask);
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
@PostMapping
public ResponseEntity<Task> createTask(@RequestBody TaskRequest taskRequest) {
    // Fetch user from DB
    User user = userService.getUserById(taskRequest.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

    // Map TaskRequest to Task
    Task task = new Task();
    task.setTitle(taskRequest.getTitle());
    task.setDescription(taskRequest.getDescription());
    task.setStatus(taskRequest.getStatus());
    task.setDueDate(taskRequest.getDueDate());
    task.setCompleted(taskRequest.isCompleted());
    task.setUser(user);

    Task createdTask = taskService.createTask(task);
    return ResponseEntity.ok(createdTask);
}

    //Get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // Get task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }
    //Update task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(task);
    }

    // Delete task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // (Optional) Get tasks by user ID
//    @GetMapping("/user/{userId}")
//    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
//        List<Task> tasks = taskService.getTasksByUserId(userId);
//        return ResponseEntity.ok(tasks);
//    }
}
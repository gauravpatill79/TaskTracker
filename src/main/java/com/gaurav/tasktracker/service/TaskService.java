package com.gaurav.tasktracker.service;


import com.gaurav.tasktracker.entity.Task;
import com.gaurav.tasktracker.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.RuntimeErrorException;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    //create task
    public Task createTask (Task task){
        return taskRepository.save(task);
    }

    //Read all
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    //read by id
    public Task getTaskById(long id){
        return taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found with id : " + id));
    }

    //update by id
    public Task updateTask(long id , Task updateTask){
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found with id : " + id));

        task.setTitle(updateTask.getTitle());
        task.setDescription((updateTask.getDescription()));
        task.setDueDate((updateTask.getDueDate()));
        task.setCompleted(updateTask.isCompleted());

        return taskRepository.save(task);
    }
    // Delete
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

}

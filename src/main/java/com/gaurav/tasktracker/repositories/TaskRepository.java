package com.gaurav.tasktracker.repositories;

import com.gaurav.tasktracker.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
//    List<Task> findByUserId(Long userId);
}

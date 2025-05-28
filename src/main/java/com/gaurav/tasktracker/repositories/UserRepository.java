package com.gaurav.tasktracker.repositories;

import com.gaurav.tasktracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}

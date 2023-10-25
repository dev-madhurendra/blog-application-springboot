package com.blog.application.apis.repositories;

import com.blog.application.apis.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

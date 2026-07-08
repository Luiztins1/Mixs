package com.github.Luiztins1.mixs.repository;

import com.github.Luiztins1.mixs.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

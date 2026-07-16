package com.github.Luiztins1.mixs.repository;

import com.github.Luiztins1.mixs.model.entity.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAuthRepository extends JpaRepository<UserAuth, UUID> {
    UserAuth findByLogin(String login);
    boolean existsByLogin(String login);
    UserAuth findByEmail(String email);
    void deleteByLogin(String login);
}

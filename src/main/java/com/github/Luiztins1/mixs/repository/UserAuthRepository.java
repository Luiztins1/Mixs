package com.github.Luiztins1.mixs.repository;

import com.github.Luiztins1.mixs.model.entity.UserAuth;

public interface UserAuthRepository extends JpaRepository<UserAuth, UUID> {
    UserAuth findByLogin(String login);
    boolean existsByLogin(String login);
}

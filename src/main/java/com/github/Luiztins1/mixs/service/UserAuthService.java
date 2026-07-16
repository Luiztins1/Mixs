package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.UserAuthResponseDTO;
import com.github.Luiztins1.mixs.model.entity.UserAuth;
import com.github.Luiztins1.mixs.model.mapper.UserAuthMapper;
import com.github.Luiztins1.mixs.repository.UserAuthRepository;
import com.github.Luiztins1.mixs.security.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userAuthRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void registerUserAuth(UserAuth userAuth) {
        var password = userAuth.getPassword();
        userAuth.setPassword(passwordEncoder.encode(password));

        userAuthRepository.save(userAuth);
    }

    public List<UserAuth> findAll() {
        return userAuthRepository.findAll();
    }

    @Transactional
    public Optional<UserAuth> updateUserAuth(UUID id, UserAuth UserAuth) {
        return userAuthRepository.findById(id)
                .map(userAuthId -> {
                    userAuthId.setPassword(UserAuth.getPassword());

                    if (userAuthId.getId() == null) throw new UsernameNotFoundException("Usuário não encontado.");

                    return userAuthRepository.save(userAuthId);
                });

    }

    @Transactional
    public void cancelUserAuth(String login) {
        var userAuthValidate = userAuthRepository.findByLogin(login);
        userAuthRepository.deleteByLogin(userAuthValidate.getLogin());
    }

    public Optional<UserAuth> findById(UUID id) {
        return userAuthRepository.findById(id);
    }

    public Optional<UserAuth> findByLogin(String login) {
        return Optional.of(userAuthRepository.findByLogin(login));
    }

    public UserAuth findByEmail(String email){
        return userAuthRepository.findByEmail(email);
    }
}
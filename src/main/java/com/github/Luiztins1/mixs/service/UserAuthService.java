package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.UserAuthResponseDTO;
import com.github.Luiztins1.mixs.model.entity.UserAuth;
import com.github.Luiztins1.mixs.model.mapper.UserAuthMapper;
import com.github.Luiztins1.mixs.repository.UserAuthRepository;
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
    public void registerUserAuth(UserAuthResponseDTO userAuthResponseDTO) {
        var user = UserAuthMapper.toEntity(userAuthResponseDTO);
        var password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));

        userAuthRepository.save(user);
    }

    public List<UserAuth> findAll() {
        return userAuthRepository.findAll();
    }

    @Transactional
    public Optional<UserAuth> updateUserAuth(UUID id, UserAuthResponseDTO userAuthResponseDTO) {
        return userAuthRepository.findById(id)
                .map(userAuth -> {
                    userAuth.setPassword(userAuthResponseDTO.password());

                    if (userAuth.getId() == null) throw new UsernameNotFoundException("Usuário não encontado.");

                    return userAuthRepository.save(userAuth);
                });

    }

    @Transactional
    public void cancelUserAuth(String login) {
        var userAuthValidate = userAuthRepository.findByLogin(login)
                        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        userAuthRepository.deleteByLogin(userAuthValidate.getLogin());
    }

    public Optional<UserAuth> findById(UUID id) {
        var userAuthValidate = userAuthRepository.findById(id);

        return Optional.of(userAuthValidate)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    public Optional<UserAuth> findByLogin(String login) {
        var userAuthValidate = userAuthRepository.findByLogin(login);
        return Optional.of(userAuthValidate)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    public UserAuth findByEmail(String email){
        return userAuthRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email não encontrado."));
    }
}
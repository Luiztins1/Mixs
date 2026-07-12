package com.github.Luiztins1.mixs.service;

import com.github.Luiztins1.mixs.controller.dto.UserResponseDTO;
import com.github.Luiztins1.mixs.model.entity.User;
import com.github.Luiztins1.mixs.model.mapper.UserMapper;
import com.github.Luiztins1.mixs.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User registerUser(UserResponseDTO userResponseDTO){
        var user = UserMapper.toEntity(userResponseDTO);
        var userValidate = userRepository.findById(user.getId());

        if(userValidate.isPresent()) throw new RuntimeException("Usuário já cadastrado.");

        return userRepository.save(user);

    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> userUpdate(Integer id, UserResponseDTO userResponseDTO){
        return userRepository.findById(id)
                .map(user -> {
                    if(user.getId() == null) throw new RuntimeException("Usuário não encontrado.");

                    user.setName(userResponseDTO.name());
                    return userRepository.save(user);
                });
    }

    @Transactional
    public void deleteUser(Integer id){
        var userValidate = userRepository.findById(id);

        if(userValidate.isEmpty()) throw new RuntimeException("Usuário não encontrado.");

        userRepository.deleteById(id);
    }

    public Optional<User> findByIdUser(Integer id){
        return Optional.of(userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado.")));
    }
}

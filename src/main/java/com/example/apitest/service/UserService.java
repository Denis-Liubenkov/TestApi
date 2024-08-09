package com.example.apitest.service;

import com.example.apitest.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    public void createUser(User user) {
        user.setName(user.getName());
        user.setSurName(user.getSurName());
        user.setEmail(user.getEmail());
        if (user.getPhones().size() < 1 || user.getPhones().size() > 3) {
            throw new IllegalArgumentException("Количество номеров телефонов должно быть от 1 до 3");
        }
        user.setPhones(user.getPhones());
        if (user.getRoles().size() < 1 || user.getRoles().size() > 3) {
            throw new IllegalArgumentException("Количество ролей должно быть от 1 до 3");
        }
        user.setRoles(user.getRoles());
        userRepository.save(user);
    }

    public void updateUser(User user) {
        user.setId(user.getId());
        user.setName(user.getName());
        user.setSurName(user.getSurName());
        user.setEmail(user.getEmail());
        user.setPhones(user.getPhones());
        user.setRoles(user.getRoles());
        userRepository.saveAndFlush(user);
    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }
}

package com.nearcodeconsulting.exercise.domain.service;

import com.nearcodeconsulting.exercise.domain.exception.DomainException;
import com.nearcodeconsulting.exercise.domain.model.User;
import com.nearcodeconsulting.exercise.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User get(Long id) {
        Optional<User> user = userRepository.findById(id);

        return user.orElse(null);
    }

    public User save(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser != null && !existingUser.equals(user)) {
            throw new DomainException("That email is already being used.");
        }

        existingUser = userRepository.findByPhone(user.getPhone());
        if (existingUser != null && !existingUser.equals(user)) {
            throw new DomainException("That phone number is already being used.");
        }

        return userRepository.save(user);
    }

    public List<User> save(List<User> users) {

        List<User> savedUsers = new ArrayList<>();

        for (User user : users) {
            savedUsers.add(save(user));
        }

        return savedUsers;
    }

    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

}

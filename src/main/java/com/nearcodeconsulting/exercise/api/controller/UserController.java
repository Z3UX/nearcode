package com.nearcodeconsulting.exercise.api.controller;

import com.nearcodeconsulting.exercise.api.model.UserRepresentationModel;
import com.nearcodeconsulting.exercise.domain.model.User;
import com.nearcodeconsulting.exercise.domain.repository.UserRepository;
import com.nearcodeconsulting.exercise.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<UserRepresentationModel> listAll() {
        return toCollectionModel(userRepository.findAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserRepresentationModel> findUser(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            UserRepresentationModel userModel = toModel(user.get());
            return ResponseEntity.ok(userModel);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserRepresentationModel add(@Valid @RequestBody User user) {
        return toModel(userService.save(user));
    }

    /*
    @PutMapping("/{userId}")
    public ResponseEntity<UserRepresentationModel> update(@Valid @PathVariable Long userId, @RequestBody User user) {

        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }

        user.setId(userId);
        user = userService.save(user);

        return ResponseEntity.ok(toModel(user));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        if (!userRepository.existsById(userId)) {
            return ResponseEntity.notFound().build();
        }

        userService.delete(userId);

        return ResponseEntity.noContent().build();
    }
    */

    private UserRepresentationModel toModel(User user ) {
        return modelMapper.map(user, UserRepresentationModel.class);
    }

    private List<UserRepresentationModel> toCollectionModel(List<User> users) {
        return users.stream()
                .map(user -> toModel(user))
                .collect(Collectors.toList());
    }

}

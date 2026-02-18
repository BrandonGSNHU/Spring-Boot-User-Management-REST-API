package com.example.usermanagement.controller;


import com.example.usermanagement.exception.ResourceNotFoundException;
import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@Valid @RequestBody User user)
    {
        return userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @Valid @RequestBody User updatedUser)
    {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setName(updatedUser.getName());
        existing.setEmail(updatedUser.getEmail());

        return userRepository.save(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id)
    {
        if (!userRepository.existsById(id))
        {
         throw new ResourceNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }
}

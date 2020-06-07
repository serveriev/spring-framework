package io.lenur.spring.blog.controller;

import io.lenur.spring.blog.dto.UserDTO;
import io.lenur.spring.blog.dto.UserResponseDTO;
import io.lenur.spring.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserResponseDTO> users() {
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public UserResponseDTO get(@PathVariable Long id) {
        return userService.get(id);
    }

    @PutMapping(value = "/{id}")
    public UserResponseDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @PostMapping
    public UserResponseDTO create(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }
}


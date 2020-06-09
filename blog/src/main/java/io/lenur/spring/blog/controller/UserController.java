package io.lenur.spring.blog.controller;

import io.lenur.spring.blog.dto.UserDto;
import io.lenur.spring.blog.dto.UserResponseDto;
import io.lenur.spring.blog.service.api.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    ApiUserService userService;

    @GetMapping
    public List<UserResponseDto> users() {
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userService.get(id);
    }

    @PutMapping(value = "/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserDto userDTO) {
        return userService.update(id, userDTO);
    }

    @PostMapping
    public UserResponseDto create(@RequestBody @Valid UserDto userDTO) throws Exception {
        return userService.create(userDTO);
    }
}


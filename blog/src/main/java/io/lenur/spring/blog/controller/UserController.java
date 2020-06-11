package io.lenur.spring.blog.controller;

import io.lenur.spring.blog.dto.UserCreateDto;
import io.lenur.spring.blog.dto.UserResponseDto;
import io.lenur.spring.blog.dto.UserUpdateDto;
import io.lenur.spring.blog.service.api.ApiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private ApiUserService userService;

    @GetMapping
    public List<UserResponseDto> users() {
        return userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return userService.get(id);
    }

    @PutMapping(value = "/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserUpdateDto dto) {
        return userService.update(id, dto);
    }

    @PostMapping
    public UserResponseDto create(@RequestBody @Valid UserCreateDto dto) {
        return userService.create(dto);
    }
}


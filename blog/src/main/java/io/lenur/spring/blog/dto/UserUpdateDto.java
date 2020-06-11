package io.lenur.spring.blog.dto;

import javax.validation.constraints.NotEmpty;

public class UserUpdateDto {
    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

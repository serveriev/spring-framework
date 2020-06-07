package io.lenur.spring.blog.dto;

public class UserResponseDTO {
    private Long Id;
    private String name;

    public UserResponseDTO(Long id, String name) {
        Id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}

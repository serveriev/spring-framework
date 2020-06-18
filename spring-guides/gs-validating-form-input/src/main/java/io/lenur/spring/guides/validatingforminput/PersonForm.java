package io.lenur.spring.guides.validatingforminput;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class PersonForm {
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Min(18)
    private Integer age;
}
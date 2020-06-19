package io.lenur.spring.guides.producingwebservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private String name;
    private String capital;
    private Currency currency;
    private int population;
}

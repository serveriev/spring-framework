package io.lenur.spring.guides.producingwebservice.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Endpoint
public class GetCountryResponse {
    private Country country;
}

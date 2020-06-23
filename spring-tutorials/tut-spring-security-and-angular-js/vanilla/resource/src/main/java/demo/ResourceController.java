package demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    @RequestMapping("/")
    @CrossOrigin(origins = "*", maxAge = 3600)
    public Message home() {
        return new Message("Hello World");
    }
}

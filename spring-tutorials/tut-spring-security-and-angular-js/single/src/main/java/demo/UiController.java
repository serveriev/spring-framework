package demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.UUID;

@Controller
public class UiController {
    @GetMapping("/user")
    @ResponseBody
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/resource")
    @ResponseBody
    public ResponseDto resource() {
        return new ResponseDto(UUID.randomUUID().toString(), "Hello World");
    }

    @GetMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "home";
    }
}

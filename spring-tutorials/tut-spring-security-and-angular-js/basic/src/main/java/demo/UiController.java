package demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UiController {
    @RequestMapping("/resource")
    public Map<String, Object> resource() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");

        return model;
    }

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}

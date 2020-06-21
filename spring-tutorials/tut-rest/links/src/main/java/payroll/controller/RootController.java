package payroll.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {
    @GetMapping(value = "/root-info")
    public RepresentationModel<?> rootInfo() {
        RepresentationModel<?> rootModel = new RepresentationModel<>();
        rootModel.add(linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
        rootModel.add(WebMvcLinkBuilder.linkTo(methodOn(OrderController.class).all()).withRel("orders"));

        return rootModel;
    }
}

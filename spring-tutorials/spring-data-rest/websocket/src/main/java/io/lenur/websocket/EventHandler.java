package io.lenur.websocket;

import lombok.AllArgsConstructor;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
@RepositoryEventHandler(Employee.class)
public class EventHandler {
    private static final String MESSAGE_PREFIX = "/topic";

    private final SimpMessagingTemplate websocket;
    private final EntityLinks entityLinks;

    @HandleAfterCreate
    public void newEmployee(Employee employee) {
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/newEmployee", getPath(employee));
    }

    @HandleAfterDelete
    public void deleteEmployee(Employee employee) {
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/deleteEmployee", getPath(employee));
    }

    @HandleAfterSave
    public void updateEmployee(Employee employee) {
        this.websocket.convertAndSend(MESSAGE_PREFIX + "/updateEmployee", getPath(employee));
    }

    private String getPath(Employee employee) {
        return this.entityLinks
                .linkForItemResource(employee.getClass(), employee.getId())
                .toUri()
                .getPath();
    }
}
package io.lenur.websocket;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DatabaseLoader implements CommandLineRunner {
    private final EmployeeRepository repository;

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Employee("Frodo", "Baggins", "ring bearer"));
        this.repository.save(new Employee("Bilbo", "Baggins", "burglar"));
        this.repository.save(new Employee("Gandalf", "the Grey", "wizard"));
        this.repository.save(new Employee("Samwise", "Gamgee", "gardener"));
        this.repository.save(new Employee("Meriadoc", "Brandybuck", "pony rider"));
        this.repository.save(new Employee("Peregrin", "Took", "pipe smoker"));
    }
}
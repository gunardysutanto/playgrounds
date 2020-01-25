package hello.controller;

import hello.model.Hello;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    @ResponseStatus(HttpStatus.FOUND)
    public Hello sayHello(@RequestParam(value = "person", required = false, defaultValue = "") String person) {
        return Hello.builder().salutation((person.isBlank()?"Hello World": String.format("Hello, %s",person))).build();
    }
}

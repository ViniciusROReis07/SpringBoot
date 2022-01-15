package br.com.springboot.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.springboot.model.User;

@RestController
@RequestMapping("/user")

public class UserController {

    private List<User> users = new ArrayList<>();

    @GetMapping("/{id}")
    public User user(@PathVariable("id") int id) {

        Optional<User> userFind = users.stream().filter(user -> user.getId() == id).findFirst();

        if (userFind.isPresent()) {
            return userFind.get();
        }

        return null;
    }

    @PostMapping("/")
    public User register(@RequestBody User user) {
        this.users.add(user);
        return user;
    }

    @GetMapping("/list")
    public List<User> list() {
        return this.users;
    }

}

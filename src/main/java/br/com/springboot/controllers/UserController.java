package br.com.springboot.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.springboot.model.User;
import br.com.springboot.repository.UserRepository;

@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User user(@PathVariable("id") int id) {

        Optional<User> userFind = userRepository.findById(id);

        if (userFind.isPresent()) {
            return userFind.get();
        }

        return null;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<User> list() {
        return this.userRepository.findAll();
    }

    @GetMapping("/list/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> listMore(@PathVariable("id") int id) {
        return this.userRepository.findByIdGreaterThan(id);
    }

    @GetMapping("/findByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<User> findByName(@PathVariable("name") String name) {
        return this.userRepository.findByNameIgnoreCase(name);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteUser(@PathVariable("id") int id) {

        Optional<User> userFind = userRepository.findById(id);

        if (userFind.isPresent()) {
            this.userRepository.deleteById(id);
            return "Usuario " + id + " excluido com sucesso";
        }

        return "Não foi possivel excluir o usuário " + id;
    }

    @PutMapping("/alter/{id}")
    public User alterUser(@PathVariable("id") int id, @RequestBody User user) {

        User userCurrent = this.userRepository.findById(id).get();

        BeanUtils.copyProperties(user, userCurrent, "id");

        return this.userRepository.save(userCurrent);
    }
}

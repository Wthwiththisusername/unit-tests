package test.crud.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import test.crud.repository.UserRepository;
import test.crud.users.User;


@RestController
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "Hello, new alien:)";
    }

    @PostMapping("/addUser")
    public User saveData(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/getUser/{id}")
    public User getUserByID(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        User user1 = user.orElse(null);
        return user1;
    }
    @GetMapping("/getAll")
    public List<User> getAll() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/removeUser/{id}")
    public String deleteUser(@PathVariable int id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            return "Minus 1 person:(";
        } else {
            return "User with ID " + id + " not found";
        }
    }
}

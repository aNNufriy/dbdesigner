package ru.testfield.algorithm.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.testfield.web.repository.UserRepository;
import ru.testfield.web.model.cms.User;

/**
 * Created by J.Bgood on 11/29/17.
 */
@RestController("restIndexController")
@RequestMapping("/api")
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/users")
    public Iterable users(){
        Iterable<User> users = userRepository.findAll();
        return users;
    }

}

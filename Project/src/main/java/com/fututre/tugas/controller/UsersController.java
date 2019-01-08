package com.fututre.tugas.controller;

import com.fututre.tugas.model.Users;
import com.fututre.tugas.repository.UsersRepository;
import com.fututre.tugas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @GetMapping("/users")
    public void getAllUsers(){
        usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @Cacheable(key = "#id", value = "users") public Users findUsers(@PathVariable("id") String id) {
        return usersRepository.findOne(id);
    }

    @PostMapping("/users")
    public Users createUser(@RequestBody Users user){
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            usersService.createUser(user);
            return usersService.createUser(user);
        } catch (Exception e) {
            return usersService.createUser(user);
        }

    }

    @PutMapping("/users/{id}")
    @CacheEvict(value = "users",key = "#users.id")
    public Users editUsers(@RequestBody Users users, @PathVariable String id){
       return usersService.editUser(users,id);
    }

    @DeleteMapping("/users/{id}")
    @CacheEvict(value = "users",key = "#users.id")
    public Boolean deleteUsers(@PathVariable String id){
        return usersService.deleteUser(id);
    }

}

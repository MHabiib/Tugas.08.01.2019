package com.fututre.tugas.controller;

import com.fututre.tugas.model.Users;
import com.fututre.tugas.repository.UsersRepository;
import com.fututre.tugas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    UsersService usersService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    @Cacheable(key = "#id", value = "users") public Users findUsers(@PathVariable("id") String id) {
        return usersService.findOneBy(id);
    }

    @PostMapping("/users")
    public boolean createUser(@RequestBody Users user){
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(user.getPassword()));
            return usersService.createUser(user);

    }

    @PutMapping("/users/{id}")
    @CacheEvict(value = "users",key = "#id")
    public Users editUsers(@RequestBody Users users, @PathVariable("id") String id){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        users.setPassword(encoder.encode(users.getPassword()));
        return usersService.editUser(users,id);
    }

    @DeleteMapping("/users/{id}")
        @CacheEvict(value = "users",key = "#id")
    public boolean deleteUsers(@PathVariable("id") String id){
         return usersService.deleteUser(id);
    }

}

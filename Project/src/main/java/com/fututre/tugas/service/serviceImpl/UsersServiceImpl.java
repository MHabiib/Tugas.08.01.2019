package com.fututre.tugas.service.serviceImpl;

import com.fututre.tugas.model.Users;
import com.fututre.tugas.repository.UsersRepository;
import com.fututre.tugas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl  implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public Users editUser(Users newUser, String id) {
        Users user = usersRepository.findOne(id);
        user.setUsername(newUser.getUsername());
        user.setRole(newUser.getRole());
        user.setPassword((newUser.getPassword()));
        return usersRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}

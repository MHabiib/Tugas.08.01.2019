package com.fututre.tugas.service.serviceImpl;

import com.fututre.tugas.model.Users;
import com.fututre.tugas.repository.UsersRepository;
import com.fututre.tugas.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl  implements UsersService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public Users createUser(Users user) {
        Users userExist=usersRepository.findByUsername(user.getUsername());
        if (userExist==null)
            return usersRepository.save(user);
        else return null;
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
    public Boolean deleteUser(String id) {
        Users userExist=usersRepository.findOne(id);
        if (userExist==null){
            usersRepository.delete(id);
            return true;}
        else
            return false;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }





}

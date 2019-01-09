package com.fututre.tugas.service;

import com.fututre.tugas.model.Users;

import java.util.List;

public interface UsersService {

    boolean createUser (Users user);

    Users editUser (Users user,String id);

    boolean deleteUser (String id);

    Users findOneBy(String id);

    List<Users> getAllUsers ();

}

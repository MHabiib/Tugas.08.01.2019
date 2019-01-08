package com.fututre.tugas.service;

import com.fututre.tugas.model.Users;

import java.util.List;

public interface UsersService {

    Users createUser (Users user);

    Users editUser (Users user,String id);

    List<Users> getAllUsers ();

}

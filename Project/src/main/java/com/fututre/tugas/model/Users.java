package com.fututre.tugas.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Users implements Serializable {
    private String username;
    private String password;
    private String role;
}

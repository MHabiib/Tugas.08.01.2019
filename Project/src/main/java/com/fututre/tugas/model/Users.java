package com.fututre.tugas.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Data
public class Users implements Serializable {

    @Id
    private String id;
    private String username;
    private String password;
    private String role;
}

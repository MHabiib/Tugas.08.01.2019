package com.fututre.tugas.repository;

import com.fututre.tugas.model.Users;
import org.springframework.boot.autoconfigure.data.mongo.MongoRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
    UsersRepository findByUsername(String username);
}

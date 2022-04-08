package com.backend.anmLogin.demo.repository;

import com.backend.anmLogin.demo.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByName(String username);
}

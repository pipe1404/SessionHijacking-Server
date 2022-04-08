package com.backend.anmLogin.demo.repository;

import com.backend.anmLogin.demo.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long> {
    @Query(value = "select ur.Role.name from UserRole ur where ur.User.id = :uid")
    List<String> findAllByUserID(Long uid);

    Role findByName(String name);
}

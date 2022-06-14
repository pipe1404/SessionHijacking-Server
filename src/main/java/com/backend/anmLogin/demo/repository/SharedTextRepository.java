package com.backend.anmLogin.demo.repository;

import com.backend.anmLogin.demo.entity.SharedText;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SharedTextRepository extends CrudRepository<SharedText, UUID> {
}

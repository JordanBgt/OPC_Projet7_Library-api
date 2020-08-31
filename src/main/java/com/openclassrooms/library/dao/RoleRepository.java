package com.openclassrooms.library.dao;

import com.openclassrooms.library.entity.ERole;
import com.openclassrooms.library.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

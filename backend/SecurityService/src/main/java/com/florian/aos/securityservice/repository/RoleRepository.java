package com.florian.aos.securityservice.repository;

import com.florian.aos.securityservice.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    boolean existsByRoleIgnoreCase(String role);
    Optional<Role> findByRole(String role);
}

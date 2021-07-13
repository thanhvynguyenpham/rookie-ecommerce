package com.rookie.ecommerce.repository;

import com.rookie.ecommerce.entity.Role;
import com.rookie.ecommerce.entity.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);
}

package com.ecommerce.sb_ecom.project.repositories;

import com.ecommerce.sb_ecom.project.model.AppRole;
import com.ecommerce.sb_ecom.project.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}

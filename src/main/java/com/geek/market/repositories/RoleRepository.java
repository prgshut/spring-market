package com.geek.market.repositories;


import com.geek.market.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Collection<Role> findRolesByNameEquals(String name);
}

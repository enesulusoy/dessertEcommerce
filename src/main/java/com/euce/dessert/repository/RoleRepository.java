package com.euce.dessert.repository;


import com.euce.dessert.model.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsByName(String name);
    Role findByName(String name);
}

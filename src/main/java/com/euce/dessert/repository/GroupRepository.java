package com.euce.dessert.repository;

import com.euce.dessert.model.account.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group, Long> {
    Boolean existsByName(String name);
    Group findByName(String name);
}

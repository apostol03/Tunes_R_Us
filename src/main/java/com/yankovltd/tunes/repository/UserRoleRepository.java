package com.yankovltd.tunes.repository;

import com.yankovltd.tunes.model.entity.UserRole;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByRole(UserRoleEnum guest);
}

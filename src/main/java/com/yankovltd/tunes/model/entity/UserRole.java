package com.yankovltd.tunes.model.entity;

import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserRole extends BaseEntity {

    private UserRoleEnum role;

    public UserRole() {
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRoleEnum getRole() {
        return role;
    }

    public UserRole setRole(UserRoleEnum role) {
        this.role = role;
        return this;
    }
}

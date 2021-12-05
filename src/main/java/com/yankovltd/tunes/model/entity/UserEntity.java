package com.yankovltd.tunes.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@NamedEntityGraph(
        name = "users_entity_graph",
        attributeNodes = {
                @NamedAttributeNode("roles")
        }
)
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String imageUrl;
    private List<UserRole> roles;

    public UserEntity() {
    }

    @Column(nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @Column(nullable = false)
    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @Column(nullable = false, unique = true)
    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    @Column(nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public List<UserRole> getRoles() {
        return roles;
    }

    public UserEntity setRoles(List<UserRole> roles) {
        this.roles = roles;
        return this;
    }

}

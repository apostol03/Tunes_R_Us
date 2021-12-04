package com.yankovltd.tunes.model.view;

public class UserPanelViewModel {

    private Long id;
    private String username;
    private String role;

    public UserPanelViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UserPanelViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserPanelViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserPanelViewModel setRole(String role) {
        this.role = role;
        return this;
    }
}

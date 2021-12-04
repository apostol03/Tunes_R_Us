package com.yankovltd.tunes.model.view;

public class UserProfileViewModel {
    private Long id;
    private String role;
    private String firstName;
    private String lastName;
    private int quizPoints;

    public UserProfileViewModel() {
    }

    public String getRole() {
        return role;
    }

    public UserProfileViewModel setRole(String role) {
        this.role = role;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getQuizPoints() {
        return quizPoints;
    }

    public UserProfileViewModel setQuizPoints(int quizPoints) {
        this.quizPoints = quizPoints;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}

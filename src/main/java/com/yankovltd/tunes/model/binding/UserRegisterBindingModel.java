package com.yankovltd.tunes.model.binding;

import com.yankovltd.tunes.model.validator.UniqueEmail;
import com.yankovltd.tunes.model.validator.UniqueUsername;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterBindingModel {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;

    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private static final String USERNAME_PATTERN = "^(?![_.])(?!.*[_.]{2})[a-z0-9._]+(?<![_.])$";

    public UserRegisterBindingModel() {
    }

    @NotNull
    @Length(min = 1, max = 15, message = "First name must be between 1 and 15 characters long")
    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    @NotNull
    @Length(min = 1, max = 15, message = "Last name must be between 1 and 15 characters long")
    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    @UniqueEmail
    @NotNull
    @Pattern(regexp = EMAIL_PATTERN,
            message = "Invalid email format")
    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    @UniqueUsername
    @NotNull
    @Pattern(regexp = USERNAME_PATTERN,
            message = "Username can contain lowercase letters, numbers, \"_\" and \".\"")
    public String getUsername() {
        return username;
    }

    public UserRegisterBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    @NotNull
    @Length(min = 4, max = 20, message = "Password must be between 4 and 20 characters long")
    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}

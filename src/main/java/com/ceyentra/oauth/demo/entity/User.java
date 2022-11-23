package com.ceyentra.oauth.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
//    @JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column
    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

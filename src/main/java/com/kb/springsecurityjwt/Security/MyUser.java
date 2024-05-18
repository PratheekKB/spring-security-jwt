package com.kb.springsecurityjwt.Security;


import jakarta.persistence.*;

@Entity
@Table(name="users")

public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private boolean enabled;
    private String role;

    public MyUser() {}

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}

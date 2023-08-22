package com.sincera.intern.dto;

import com.sincera.intern.model.Role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UserDto {
    private Long id;
    private Set<Role> roles;
    private Date createdDate;
    private String username;
    private String password;
    private boolean enabled;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
//                ", role='" + role + '\'' +
                ", createdDate=" + createdDate +
                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }



    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

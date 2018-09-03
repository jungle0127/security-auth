package com.ps.security.auth.authorize.rbac.dto;

import org.hibernate.validator.constraints.NotBlank;

public class AdminInfo {
    private Long id;
    @NotBlank(message="role id can not be null.")
    private Long roleId;
    @NotBlank(message = "user name can not be null.")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

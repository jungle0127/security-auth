package com.ps.security.auth.authorize.rbac.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Admin  implements UserDetails{
    /**
     *  Primary Key of the table
     */
    @Id
    @GeneratedValue
    private Long id;
    /**
     * Audit log, recored the create time of current item, assign value automatically
     * It don't need assign value by developer.
     */
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createTime;
    /**
     * User name
     */
    private String username;
    /**
     * password     *
     */
    private String password;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.REMOVE)
    private Set<RoleAdmin> roles = new HashSet<>();
    @Transient
    private Set<String> urls = new HashSet<>();
    @Transient
    private Set<Long> resourceIds = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}

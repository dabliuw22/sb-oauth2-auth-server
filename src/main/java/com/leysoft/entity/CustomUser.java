
package com.leysoft.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Entity
@Table(
        name = "users")
public class CustomUser implements Serializable {

    private static final long serialVersionUID = -2787073619010925563L;

    @Id
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq")
    private Long id;

    @Column(
            nullable = false,
            unique = true)
    private String username;

    @Column(
            nullable = false)
    private String password;

    @Column(
            name = "is_enabled",
            nullable = false)
    private boolean enabled;

    @ManyToMany(
            fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_roles",
            joinColumns = {
                @JoinColumn(
                        name = "user_id",
                        nullable = false)
            },
            inverseJoinColumns = {
                @JoinColumn(
                        name = "role_id",
                        nullable = false)
            })
    private Set<CustomRole> roles = new HashSet<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_id")
    private Set<CustomClientDetails> clients;

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

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<CustomRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<CustomRole> roles) {
        this.roles = roles;
    }

    public Set<CustomClientDetails> getClients() {
        return clients;
    }

    public void setClients(Set<CustomClientDetails> clients) {
        this.clients = clients;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Set<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return authorities;
    }

    @Override
    public String toString() {
        return "CustomUserDetails [id=" + id + ", username=" + username + ", password=" + password
                + ", enabled=" + enabled + ", roles=" + roles + ", clients=" + clients + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomUser other = (CustomUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}

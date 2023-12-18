package org.java.spring.auth.db.pojo;


import java.util.Collection;
import java.util.List;

import org.java.spring.db.pojo.Photo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User implements UserDetails {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Photo> photos;
    
    public User() { }
    public User(String username, String password, Role... roles) {
        
        setUsername(username);
        setPassword(password);
        setRoles(roles);
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    
    public List<Role> getRoles() {
        return roles;
    }
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    public void setRoles(Role... roles) {
        setRoles(List.of(roles));
    }
    
    public List<Photo> getPhotos() {
        return photos;
    }
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
    
    public boolean isAdmin() {
        return roles.stream().anyMatch(role -> role.getName().equals("ADMIN"));
    }

    public boolean isSuperAdmin() {
            return roles.stream().anyMatch(role -> role.getName().equals("SUPER-ADMIN"));
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
            .toList();
    }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
    
    @Override
    public String toString() {
        
        return "[" + getId() + "] " + getUsername();
    }
}
package org.java.spring.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.java.spring.auth.db.pojo.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 35, nullable = false)
    @NotBlank(message = "Il nome non può essere vuoto")
    @Length(min = 3, max = 35, message = "Il nome della foto deve essere compreso tra 3 e 35 caratteri")
    private String name;
    
    @Column(columnDefinition = "TEXT")
    @Length(min = 5, max = 500, message = "La descrizione della foto deve essere compresa tra 5 e 500 caratteri")
    private String description;
    
    @Column(nullable = false)
    @NotBlank(message = "URL non può essere vuoto")
    @URL(protocol = "https", message = "Devi inserire un URL valido (Protocollo HTTPS)")
    private String url;
    
    @Column(nullable = false)
    private boolean visible;
    
    private boolean checked;
    
    @ManyToMany
    @Column(nullable = false)
    @Size(min = 1, message = "Devi selezionare almeno una categoria")
    private List<Category> categories;

    @ManyToOne
    private User user;
    
    public Photo() { }
    public Photo (String name, String description, String url, boolean visible, User user, Category... categories) {
        setName(name);
        setDescription(description);
        setUrl(url);
        setVisible(visible);
        setUser(user);
        setCategories(categories);
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public boolean isVisible() {
        return visible;
    }
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Category> getCategories() {
        return categories;
    }
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    public void setCategories(Category... categories) {
        setCategories(Arrays.asList(categories));
    }
    
    @Override
    public String toString() {
        return "[" + getId() + "] " + getName();
    }
}
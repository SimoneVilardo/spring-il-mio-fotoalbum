package org.java.spring.db.pojo;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(length = 50, nullable = false)
    @NotBlank(message = "Il nome non può essere vuoto")
    @Length(min = 3, max = 50, message = "Il nome della categoria deve essere compreso tra 3 e 50 caratteri")
    private String name;
    
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Photo> photos;
	
    
    public Category() { }
    
    public Category (String name) {
        setName(name);
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
    
    public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	@Override
    public String toString() {
        return "[" + getId() + "] " + getName();
    }
}
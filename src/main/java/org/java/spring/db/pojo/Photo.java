package org.java.spring.db.pojo;

import java.util.Arrays;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;

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
    @NotBlank(message = "Url non può essere vuoto")
    @URL(protocol = "https", message = "Devi inserire un URL valido (Protocollo HTTPS)")
    private String url;
	
    @Column(nullable = false)
	private boolean visible;
    
    @ManyToMany
    private List<Category> categories;
	
	
	public Photo() {}
	
	public Photo(String name, String description, String url, boolean visible, Category... categories) {
		
		setName(name);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
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

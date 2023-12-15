package org.java.spring.db.pojo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Photo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 32, nullable = false)
    @NotBlank(message = "Il nome non può essere vuoto")
    @Length(min = 3, max = 20, message = "Il nome della pizza deve essere compreso tra 3 e 20 caratteri")
    private String name;
    
    @Column(columnDefinition = "TEXT")
    @Length(min = 5, max = 100, message = "La descrizione della pizza deve essere compresa tra 5 e 100 caratteri")
    private String description;
    
    @Column(nullable = false)
    @URL(protocol = "https", message = "Devi inserire un URL valido (Protocollo HTTPS)")
    private String url;
	
    @Column(nullable = false)
	private boolean visible;
	
	
	public Photo() {}
	
	public Photo(String name, String description, String url, boolean visible) {
		
		setName(name);
		setDescription(description);
		setUrl(url);
		setVisible(visible);
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
	
	  @Override
	    public String toString() {
	        return "[" + getId() + "] " + getName();
	    }
	
}

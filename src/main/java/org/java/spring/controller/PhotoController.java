package org.java.spring.controller;

import java.util.List;

import org.java.spring.auth.db.pojo.User;
import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.CategoryService;
import org.java.spring.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class PhotoController {

	@Autowired
	private PhotoService photoService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public String getPhotos(Model model, @RequestParam(required = false) String name) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication != null && authentication.getPrincipal() instanceof User) {
			
			User user = (User) authentication.getPrincipal();
			
			if(user.isAdmin()) {
				
				// Se l'utente è un admin, mostra solo le foto associate al suo ID
				List<Photo> photos = name == null
                        ? photoService.findByUserId(user.getId())
                        : photoService.findByName(name, user.getId());

                model.addAttribute("photos", photos);
                
			} else if(user.isSuperAdmin()) {
				
				 // Se l'utente è un superadmin, mostra tutte le foto
                List<Photo> photos = name == null
                        ? photoService.findAll()
                        : photoService.findByNameSuperAdmin(name);

                model.addAttribute("photos", photos);
			}
		
			model.addAttribute("name", name == null ? "" : name);
		}
		
		return "photos";
	}
	
	@GetMapping("/photos/{id}")
	public String getPhoto(Model model, @PathVariable int id) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User user = (User) authentication.getPrincipal();
		
		Photo photo = photoService.findById(id);
		
		if(photo.getUser().getId() == user.getId() || user.isSuperAdmin()) {
			
			List<Category> categories = photo.getCategories();
			
			model.addAttribute("photo", photo);
			
			model.addAttribute("categories", categories);
			
			return "photo";
			
		} else {
			
			return "redirect:/";
		}
	}
	
	@GetMapping("/photos/create")
	public String createPhoto(Model model) {
		
		Photo photo = new Photo();
		List<Category> categories = categoryService.findAll();
		
		model.addAttribute("photo", photo);
		model.addAttribute("categories", categories);
		
		return "photo-form";
	}
	@PostMapping("/photos/create")
	public String storePhoto(Model model, @Valid @ModelAttribute Photo photo, BindingResult bindingResult, Authentication authentication) {

		if (authentication != null && authentication.getPrincipal() instanceof User) {
			
            User user = (User) authentication.getPrincipal();
            
            photo.setUser(user); 
        }
		
		return savePhoto(model, photo, bindingResult);
	}
	
	@GetMapping("photos/edit/{id}")
	public String editPhoto(Model model, @PathVariable int id) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User user = (User) authentication.getPrincipal();
		
		Photo photo = photoService.findById(id);
		
		if(photo.getUser().getId() == user.getId() || user.isSuperAdmin()) {
            
			List<Category> categories = categoryService.findAll();
			
			model.addAttribute("photo", photo);
			model.addAttribute("categories", categories);
			
			return "photo-form";
			
        } else {
        	
            return "redirect:/";
        }
	}
	@PostMapping("photos/edit/{id}")
	public String updatePhoto(Model model, @Valid @ModelAttribute Photo updatedPhoto, BindingResult bindingResult, Authentication authentication) {
        
		if (bindingResult.hasErrors()) {
			
            model.addAttribute("photo", updatedPhoto);
            
            return "photo-form";
        }

        if (authentication != null && authentication.getPrincipal() instanceof User) {
        	
            User user = (User) authentication.getPrincipal();
            
            Photo photo = photoService.findById(updatedPhoto.getId());

            if (user.isSuperAdmin()) {
            	
                photo.setVisible(updatedPhoto.isVisible());
                photo.setChecked(updatedPhoto.isChecked());
                
                if (photo.isChecked()) {
                    photo.setVisible(false);
                }

            } else {
            	
                photo.setName(updatedPhoto.getName());
                photo.setDescription(updatedPhoto.getDescription());
                photo.setUrl(updatedPhoto.getUrl());
                photo.setVisible(updatedPhoto.isVisible());
                photo.setCategories(updatedPhoto.getCategories());
            }

            photoService.save(photo);
        }

        return "redirect:/";
    }

	@PostMapping("/photos/delete/{id}")
	public String deletePhoto(@PathVariable int id) {
		
		Photo photo = photoService.findById(id);
		photoService.delete(photo);
		
		System.out.println(photo);
		
		return "redirect:/";
	}
	
	private String savePhoto(Model model, @Valid @ModelAttribute Photo photo, BindingResult bindingResult) {

		System.out.println("Photo:\n" + photo);
		System.out.println("\n---------------\n");
		System.out.println("Error:\n" + bindingResult);

		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult);
			
			List<Category> categories = categoryService.findAll();

			model.addAttribute("photo", photo);
			model.addAttribute("categories", categories);
			
			return "photo-form";
		}

		photoService.save(photo);

		return "redirect:/";
	}
}
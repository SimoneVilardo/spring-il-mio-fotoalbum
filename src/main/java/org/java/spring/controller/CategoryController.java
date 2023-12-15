package org.java.spring.controller;

import java.util.List;

import org.java.spring.db.pojo.Category;
import org.java.spring.db.pojo.Photo;
import org.java.spring.db.serv.CategoryService;
import org.java.spring.db.serv.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

@Controller
public class CategoryController {
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private PhotoService photoService;
    
    @GetMapping("/categories")
    public String getCategories(Model model) {
        
        List<Category> categories = categoryService.findAll();
        
        model.addAttribute("categories", categories);
        
        return "categories";
    }
    
    @GetMapping("/categories/create")
    public String createCategory(Model model) {
        
        Category category = new Category();
        
        model.addAttribute("category", category);
        
        return "category-form";
    }
    @PostMapping("/categories/create")
    public String storeCategory(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {

        return saveCategory(model, category, bindingResult);
    }
    
    @GetMapping("categories/edit/{id}")
    public String editCategory(Model model, @PathVariable int id) {
        
        Category category = categoryService.findById(id);
        
        model.addAttribute("category", category);
        
        return "category-form";
    }
    @PostMapping("categories/edit/{id}")
    public String updateCategory(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {
        
        return saveCategory(model, category, bindingResult);
    }
    
    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id) {
        
        Category category = categoryService.findById(id);
        
        // RIMOZIONE RELAZIONI TRA LA CATEGORIA E LA FOTO
        List<Photo> categoriesPhotos = category.getPhotos();
        
        categoriesPhotos.forEach(photo -> {
            photo.getCategories().remove(category);
            photoService.save(photo);
        });
        
        categoryService.delete(category);
        
        System.out.println(category);
        
        return "redirect:/categories";
    }
    
    private String saveCategory(Model model, @Valid @ModelAttribute Category category, BindingResult bindingResult) {

        System.out.println("Category:\n" + category);
        System.out.println("\n---------------\n");
        System.out.println("Error:\n" + bindingResult);

        if (bindingResult.hasErrors()) {

            System.out.println(bindingResult);
            
            model.addAttribute("category", category);
            
            return "category-form";
        }

        categoryService.save(category);

        return "redirect:/categories";
    }
}
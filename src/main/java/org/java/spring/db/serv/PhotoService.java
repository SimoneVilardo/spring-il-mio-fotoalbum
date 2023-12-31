package org.java.spring.db.serv;

import java.util.List;

import org.java.spring.db.pojo.Photo;
import org.java.spring.db.repo.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {
    
    @Autowired
    private PhotoRepository photoRepository;
    
    public List<Photo> findAll(){
        return photoRepository.findAll();
    }
    public Photo findById(int id) {
        return photoRepository.findById(id).get();
    }
    public List<Photo> findByName(String name, int userId){
        return photoRepository.findByNameContainingIgnoreCaseAndUser_Id(name, userId);
    }
    public List<Photo> findByNameSuperAdmin(String name){
        return photoRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Photo> findByUserId(int id){
        return photoRepository.findByUserId(id);
    }
    public List<Photo> findByVisibleTrue(){
        return photoRepository.findByVisibleTrue();
    }
    public void save(Photo photo) {
        photoRepository.save(photo);
    }
    public void delete(Photo photo) {
        
        photoRepository.delete(photo);
    }
    public void deleteById(int id) {
        
        photoRepository.deleteById(id);
    }
}
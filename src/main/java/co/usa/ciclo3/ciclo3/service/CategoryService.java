/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.repository.CategoryRepository;
import co.usa.ciclo3.ciclo3.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martinez Huertas
 */
@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAll(){
        
        return categoryRepository.getAll();
    }
        
    public Optional<Category> getCategory(int id){
            return categoryRepository.getCategory(id);
    }
   
    public Category save(Category  ca){
        if(ca.getId()==null){
            return categoryRepository.save(ca);        
        }else{
            Optional<Category> maux=categoryRepository.getCategory(ca.getId());
            if(maux.isEmpty()){
                return categoryRepository.save(ca);            
            }else{
                return ca;
            
            }
        
        }
    
    }
    
}

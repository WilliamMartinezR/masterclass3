/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.repository.crud.MachineCrudRepository;
import co.usa.ciclo3.ciclo3.model.Machine;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martinez Huertas
 */
@Repository
public class MachineRepository {
    
    @Autowired
    private MachineCrudRepository machineCrudRepository;
    
    public List<Machine> getAll(){
        
        return (List<Machine>) machineCrudRepository.findAll();
    
    }
    public Optional<Machine> getMachine(int id){
    
        return machineCrudRepository.findById(id);
    
    }
    
    public Machine save(Machine m){
    
        return machineCrudRepository.save(m);
    }
}

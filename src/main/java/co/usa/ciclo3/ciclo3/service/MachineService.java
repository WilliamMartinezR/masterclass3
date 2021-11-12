/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.model.Machine;
import co.usa.ciclo3.ciclo3.repository.MachineRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martinez Huertas
 */
@Service
public class MachineService {
    
    @Autowired
    private MachineRepository machineRepository;
    
    public List<Machine> getAll(){
        
        return machineRepository.getAll();
    }
        
    public Optional<Machine> getMachine(int id){
            return machineRepository.getMachine(id);
    }
   
    public Machine save(Machine  m){
        if(m.getId()==null){
            return machineRepository.save(m);        
        }else{
            Optional<Machine> maux=machineRepository.getMachine(m.getId());
            if(maux.isEmpty()){
                return machineRepository.save(m);            
            }else{
                return m;
            
            }
        
        }
    
    }
    public Machine update(Machine m) {
        if (m.getId() != null) {
            Optional<Machine> g = machineRepository.getMachine(m.getId());
            if (!g.isEmpty()) {
                
                if (m.getBrand() != null) {
                   g.get().setBrand(m.getBrand());
                }
                
                if (m.getName() != null) {
                    g.get().setName(m.getName());
                }
                if (m.getYear() != null) {
                    g.get().setYear(m.getYear());
                }
                if (m.getDescription() != null) {
                    g.get().setDescription(m.getDescription());
                }


                return machineRepository.save(g.get());
            }
        }
        return m;
    }

    public boolean deleteMachine(int id) {
        Optional<Machine> c = getMachine(id);
        if (!c.isEmpty()) {
            machineRepository.delete(c.get());
            return true;
        }
        return false;

    }
}

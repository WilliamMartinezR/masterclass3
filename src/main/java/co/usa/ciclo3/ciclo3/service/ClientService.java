/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.service;


import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Machine;
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
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAll(){
        
        return clientRepository.getAll();
    }
        
    public Optional<Client> getClient(int id){
            return clientRepository.getClient(id);
    }
   
    public Client save(Client  c){
        if(c.getIdClient()==null){
            return clientRepository.save(c);        
        }else{
            Optional<Client> maux=clientRepository.getClient(c.getIdClient());
            if(maux.isEmpty()){
                return clientRepository.save(c);            
            }else{
                return c;
            
            }
        
        }
    
    }
        public Client update(Client c) {
        if (c.getIdClient() != null) {
            Optional<Client> g = clientRepository.getClient(c.getIdClient());
            if (!g.isEmpty()) {
                
                
                if (c.getName() != null) {
                    g.get().setName(c.getName());
                }
                if (c.getPassword() != null) {
                    g.get().setPassword(c.getPassword());
                }
                if (c.getAge() != null) {
                    g.get().setAge(c.getAge());
                }
                if (c.getEmail() != null) {
                    g.get().setEmail(c.getEmail());
                }


                return clientRepository.save(g.get());
            }
        }
        return c;
    }

    public boolean deleteClient(int id) {
        Optional<Client> c = getClient(id);
        if (!c.isEmpty()) {
            clientRepository.delete(c.get());
            return true;
        }
        return false;

    }
}


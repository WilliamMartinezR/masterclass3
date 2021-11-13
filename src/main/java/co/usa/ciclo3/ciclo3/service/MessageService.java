/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Machine;
import co.usa.ciclo3.ciclo3.model.Message;
import co.usa.ciclo3.ciclo3.repository.ClientRepository;
import co.usa.ciclo3.ciclo3.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martinez Huertas
 */
@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> getAll(){
        
        return messageRepository.getAll();
    }
        
    public Optional<Message> getMessage(int id){
            return messageRepository.getMessage(id);
    }
   
    public Message save(Message  me){
        if(me.getIdMessage()==null){
            return messageRepository.save(me);        
        }else{
            Optional<Message> maux=messageRepository.getMessage(me.getIdMessage());
            if(maux.isEmpty()){
                return messageRepository.save(me);            
            }else{
                return me;
            
            }
        
        }
    
    }
    public Message update(Message me) {
        if (me.getIdMessage() != null) {
            Optional<Message> g = messageRepository.getMessage(me.getIdMessage());
            if (!g.isEmpty()) {
                
                if (me.getMessageText() != null) {
                   g.get().setMessageText(me.getMessageText());
                }
                
                return messageRepository.save(g.get());
            }
        }
        return me;
    }

    public boolean deleteMessage(int id) {
        Optional<Message> c = getMessage(id);
        if (!c.isEmpty()) {
            messageRepository.delete(c.get());
            return true;
        }
        return false;

    }
}

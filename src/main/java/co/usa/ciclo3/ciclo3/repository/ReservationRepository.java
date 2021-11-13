/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.repository;

import co.usa.ciclo3.ciclo3.model.Category;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.custom.CountClient;
import co.usa.ciclo3.ciclo3.repository.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martinez Huertas
 */
@Repository
public class ReservationRepository {
    
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservation> getAll(){
        
        return (List<Reservation>) reservationCrudRepository.findAll();
    
    }
    public Optional<Reservation> getReservation(int id){
    
        return reservationCrudRepository.findById(id);
    
    }
    
    public Reservation save(Reservation r){
    
        return reservationCrudRepository.save(r);
    }
    
    public void delete(Reservation r){
    
         reservationCrudRepository.delete(r);
    }
    
    public List<Reservation> getReservationsByStatus(String status){    
        return reservationCrudRepository.findAllByStatus(status);
        
    }    
    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo){    
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne,dateTwo);
    
    }
    
    public List<CountClient> getTopClients(){
    
        List<CountClient> res=new ArrayList<>();
        
        List<Object[]> report = reservationCrudRepository.countTotalReservationByClient();
        for(int i=0;i<report.size();i++){
            res.add(new CountClient((Long) report.get(i)[1],(Client)report.get(i)[0]));
        
        }
        
        return res;
    }
}

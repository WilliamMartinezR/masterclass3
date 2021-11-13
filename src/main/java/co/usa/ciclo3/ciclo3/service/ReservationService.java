/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.usa.ciclo3.ciclo3.service;

import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.custom.CountClient;
import co.usa.ciclo3.ciclo3.model.custom.StatusAmount;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martinez Huertas
 */
@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> getAll(){
        
        return reservationRepository.getAll();
    }
        
    public Optional<Reservation> getReservation(int id){
            return reservationRepository.getReservation(id);
    }
   
    public Reservation save(Reservation  r){
        if(r.getIdReservation()==null){
            return reservationRepository.save(r);        
        }else{
            Optional<Reservation> maux=reservationRepository.getReservation(r.getIdReservation());
            if(maux.isEmpty()){
                return reservationRepository.save(r);            
            }else{
                return r;
            
            }
        
        }
    
    }
    public Reservation update(Reservation r) {
        if (r.getIdReservation() != null) {
            Optional<Reservation> g = reservationRepository.getReservation(r.getIdReservation());
            if (!g.isEmpty()) {
                
                if (r.getStartDate()!= null) {
                   g.get().setStartDate(r.getStartDate());
                }
                
                if (r.getDevolutionDate() != null) {
                    g.get().setDevolutionDate(r.getDevolutionDate());
                }
                if (r.getStatus()!= null) {
                    g.get().setStatus(r.getStatus());
                }
                if (r.getScore() != null) {
                    g.get().setScore(r.getScore());
                }


                return reservationRepository.save(g.get());
            }
        }
        return r;
    }

    public boolean deleteReservation(int id) {
        Optional<Reservation> c = getReservation(id);
        if (!c.isEmpty()) {
            reservationRepository.delete(c.get());
            return true;
        }
        return false;

    }
    
    public List<CountClient> getTopClients(){
    
        return reservationRepository.getTopClients();
    
    }
    
    public StatusAmount getStatusReport(){
        List<Reservation> completed=reservationRepository.getReservationsByStatus("completed");
        List<Reservation> cancelled=reservationRepository.getReservationsByStatus("cancelled");
        
        StatusAmount descAmt=new StatusAmount(completed.size(),cancelled.size());
        return descAmt;    
    }
    public List<Reservation> getReservationPeriod(String d1, String d2){
        

        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne=new Date();
        Date dateTwo=new Date();
        try{
            dateOne=parser.parse(d1);
            dateTwo=parser.parse(d2);
        }catch (ParseException e){
            e.printStackTrace();
        
        }
        if(dateOne.before(dateTwo)){
            return reservationRepository.getReservationPeriod(dateOne, dateTwo);
        
        }else{
            return new ArrayList<>();
        
        }       
   }
}

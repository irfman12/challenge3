package com.ahmed.booking.service;



import static java.lang.String.format;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.ahmed.booking.dao.jpa.SeatRepository;
import com.ahmed.booking.domain.Seat;
import com.ahmed.booking.domain.SeatStatus;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/*
 * Main Service which takes care of booking with backend
 */
@Service
public class SeatService {

    private static final Logger log = LoggerFactory.getLogger(SeatService.class);

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    GaugeService gaugeService;

    public SeatService() {
    }
    
    public List<Seat> createSeats(int seats) {
        for (int i=0;i<seats;i++){
        	
        	createSeat(new Seat());
        }        
        return this.getAllSeats(); 
    }
    
    public void freeSeats () {
        
    	Iterable<Seat> allSeats = seatRepository.findAll();
    	for (Seat t : allSeats){
    		t = this.freeSeat(t);
    	}
    }
    
    public Seat freeSeat (Seat f){
    	f.setCode(null);
		f.setUserName(null);
		f.setStatus(SeatStatus.AVAILABLE);
		f.setReservedtime(null);
		seatRepository.save(f);
		return f;
    }
    
    public void freeBlockedSeats(){
    	log.info("Freeing Blocked Seats");
    	Iterable <Seat> blockedSeats = seatRepository.findSeatByStatus(SeatStatus.TEMPBLOCK);
    	synchronized (blockedSeats) {
    		Date current = new Date();
    	for (Seat t : blockedSeats){
    		
    	     Date reservedTime = t.getReservedtime();
    	     Calendar c = Calendar.getInstance();
    	     c.setTime(reservedTime);
    		 c.add(Calendar.MINUTE, 5);
    		 Date expiryTime = c.getTime();
    		 //Checking to see if it was reserved within 5 minutes
    		 
  			 SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
			 log.warn("Before check current={}", dateFormat.format(current));
			 log.warn("Before check expiry={}", dateFormat.format(expiryTime));
    		 if (current.after(expiryTime)){
    			 
    			 log.warn("current={}", dateFormat.format(current));
    			 log.warn("expiry={}", dateFormat.format(expiryTime));
    			 log.warn("Seat Expiring with ID={}", t.getId());
    			 this.freeSeat(t);
    		 }
    	}
    	}//end synchronize
    }
    
    
    public Seat getSeatByCode(String code){
    	return seatRepository.findSeatByCode(code);
    }
    
    public Seat createSeat(Seat seat) {
    	log.debug("calling get Seats");
        return seatRepository.save(seat);
    }

    public Seat getSeat(long id) {
        return seatRepository.findOne(id);
    }
    
    public Seat updateSeat(Seat seat) {
        return seatRepository.save(seat);
    }
   
    public void deleteSeat(Long id) {
        seatRepository.delete(id);
    }

    public List<Seat> getAllSeats() {
    	log.debug("calling get Seats");
    	Iterable<Seat> a = seatRepository.findAll();
    	List<Seat> b = Lists.newArrayList(a);
    	log.info("Value for seats={}",b.size());
    	return b;
    }
    
    public java.util.List<Seat> getAllSeatsFilter(SeatStatus status) {
        return seatRepository.findSeatByStatus(status);
    }
}

package com.ahmed.booking.scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ahmed.booking.service.SeatService;

@Component
public class FreeBlockedSeatsTask {

    private static final Logger log = LoggerFactory.getLogger(FreeBlockedSeatsTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    SeatService service;
    
    //Job runs every 1 minutes
    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
        service.freeBlockedSeats();
    }
	
}

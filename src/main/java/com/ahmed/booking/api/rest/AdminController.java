package com.ahmed.booking.api.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahmed.booking.domain.Seat;
import com.ahmed.booking.exception.SeatReservedException;
import com.ahmed.booking.service.SeatService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@RequestMapping(value = "/seats/admin")
@Api(value = "Admin Seats", description = "Admin movie seat booking")
public class AdminController {

	private static final Logger log = LoggerFactory.getLogger(AdminController.class);
	
    @Autowired
    private SeatService seatService;
    
	
@RequestMapping(value= "/createseats",
method = RequestMethod.GET,
produces = {"application/json"})
@ResponseBody
public List<Seat> createSeats (@ApiParam (value="Adding x number of seats", required=true)
		@RequestParam("num") int num, HttpServletRequest request, HttpServletResponse response
		){
	return seatService.createSeats(num);
}

@RequestMapping(value= "",
method = RequestMethod.GET,
produces = {"application/json"})
@ResponseBody
public List<Seat> getAllSeats ( HttpServletRequest request, HttpServletResponse response
		){
	return seatService.getAllSeats();
}


@RequestMapping(value="/{id}",
method = RequestMethod.PUT,
consumes = {"application/json"},
produces = {"application/json"})
@ResponseBody
public Seat modifySeat (@ApiParam (value="seat id", required=true) @PathVariable("id") long id, @RequestBody Seat seat, 
						HttpServletRequest request, HttpServletResponse response){
	Seat retrievedSeat = seatService.getSeat(id);
	if (seat.getId() == retrievedSeat.getId()){
	
	return seatService.updateSeat(seat);
	}
	else{
		throw new SeatReservedException("You are trying to update the wrong seat path/body" + id + "/" + "" + seat.getId());
	}
}

@RequestMapping(value= "/freeall",
method = RequestMethod.GET,
produces = {"application/json"})
@ResponseBody
public List<Seat> freeSeats ( HttpServletRequest request, HttpServletResponse response
		){
	seatService.freeSeats();
	return seatService.getAllSeats();
}

	
}

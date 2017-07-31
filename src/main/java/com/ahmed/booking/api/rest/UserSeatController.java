package com.ahmed.booking.api.rest;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.ahmed.booking.domain.Seat;
import com.ahmed.booking.domain.SeatStatus;
import com.ahmed.booking.exception.DataFormatException;
import com.ahmed.booking.exception.SeatReservedException;
import com.ahmed.booking.service.SeatService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */

@RestController
@RequestMapping(value = "/seats/user")
@Api(value = "seats", description = "movie seat booking")
public class UserSeatController extends AbstractRestHandler {

    @Autowired
    private SeatService seatService;

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get's all seats.", notes = "Get's all Seats")
    public
    @ResponseBody
    List<Seat> getAllSeats(HttpServletRequest request, HttpServletResponse response) {
        return this.seatService.getAllSeats();
    }
    
    @RequestMapping(value = "/free",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get's all free seats.", notes = "Get's all free Seats")
    public
    @ResponseBody
    List<Seat> getAvailableSeats(HttpServletRequest request, HttpServletResponse response) {
        return this.seatService.getAllSeatsFilter(SeatStatus.AVAILABLE);
    }
    
    @RequestMapping(value = "/status/{confCode}",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get's Status of booking")
    public
    @ResponseBody
    Seat getSeatByConfCode(@PathVariable("confCode") String code, HttpServletRequest request, HttpServletResponse response) {
        return this.seatService.getSeatByCode(code);
    }
    
    
    @RequestMapping(value="/{id}/reserve",
    		method = RequestMethod.PUT,
    		produces = {"application/json"})
    		@ResponseBody
    public Seat holdSeat (@ApiParam (value="seat id", required=true) @PathVariable("id") long id, 
    					  @ApiParam (value="userName", required=true) @RequestParam("user") String user, 
    								HttpServletRequest request, HttpServletResponse response){
    			Seat retrievedSeat = seatService.getSeat(id);
    			synchronized (retrievedSeat) {
    			if (retrievedSeat.getStatus().compareTo(SeatStatus.AVAILABLE) == 0){	
				retrievedSeat.setStatus(SeatStatus.TEMPBLOCK);
    			retrievedSeat.setUserName(user);
    			retrievedSeat.setReservedtime(new Date());
    			return seatService.updateSeat(retrievedSeat);
    			}
    			throw new SeatReservedException("Couldn't reserve seat wasn't available");
    } //End Synchnorized block
    } // End Method
    
    @RequestMapping(value="/{id}/cancel",
    		method = RequestMethod.PUT,
    		produces = {"application/json"})
    		@ResponseBody
    public Seat freeSeat (@ApiParam (value="seat id", required=true) @PathVariable("id") long id, 
    					  @ApiParam (value="userName", required=true) @RequestParam("user") String user, 
    								HttpServletRequest request, HttpServletResponse response){
    			Seat retrievedSeat = seatService.getSeat(id);
    			synchronized (retrievedSeat) {
    			if (retrievedSeat.getStatus().compareTo(SeatStatus.TEMPBLOCK) == 0 && user.equalsIgnoreCase(retrievedSeat.getUserName())){	
				retrievedSeat.setStatus(SeatStatus.AVAILABLE);
    			retrievedSeat.setUserName(null);
    			retrievedSeat.setReservedtime(null);
    			return seatService.updateSeat(retrievedSeat);
    			}
    			throw new SeatReservedException("Couldn't free seat wasn't in TEMPBLOCK status or userNames didn't match");
    } //End Synchnorized block
    } // End Method		
    @RequestMapping(value="/{id}/pay",
    		    	method = RequestMethod.PUT,
    		    	produces = {"application/json"})
    @ResponseBody
    public Seat reserveSeat (@ApiParam (value="seat id", required=true) @PathVariable("id") long id, 
    						@ApiParam (value="userName", required=true) @RequestParam("userName") String user,
    		    								HttpServletRequest request, HttpServletResponse response){
    		    			Seat retrievedSeat = seatService.getSeat(id);
    		    			synchronized (retrievedSeat) {
    		    			if (retrievedSeat.getStatus().compareTo(SeatStatus.TEMPBLOCK) == 0 && user.equalsIgnoreCase(retrievedSeat.getUserName())){	
    						retrievedSeat.setStatus(SeatStatus.PAIDRESERVED);
    						int ran = RandomUtils.nextInt(100000);
    						retrievedSeat.setCode(retrievedSeat.getUserName()+ran);
    		    			return seatService.updateSeat(retrievedSeat);
    		    			}
    		    			throw new SeatReservedException("Couldn't reserve seat wasn't in TEMPBLOCK status or userNames didn't match");
    } //End Synchnorized block			   			
    } // End Method


}

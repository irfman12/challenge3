package com.ahmed.booking.test;

/**
 * Uses JsonPath: http://goo.gl/nwXpb, Hamcrest and MockMVC
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ahmed.booking.Application;
import com.ahmed.booking.api.rest.UserSeatController;
import com.ahmed.booking.domain.Seat;
import com.ahmed.booking.domain.SeatStatus;
import com.ahmed.booking.service.SeatService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = Application.class)
@Profile("test")
public class AdminControllerTest {

    private static final String RESOURCE_LOCATION_PATTERN = "http://localhost/seats/admin/";

    @InjectMocks
    UserSeatController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() throws Exception{
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    @DirtiesContext
    public void getseats()throws Exception{
    	mvc.perform(get("/seats/admin/createseats?num=10")
    				.accept(MediaType.APPLICATION_JSON))
    				.andExpect(status().isOk())
    				.andExpect(jsonPath("$", hasSize(10)));
    	
    }
    
//    @Test
//    public void reserveSeat()throws Exception{
//    	mvc.perform(put("/seats/user/1/reserve/?user=irfan")
//    				.accept(MediaType.APPLICATION_JSON))
//    				.andExpect(status().isOk())
//    				.andExpect(jsonPath("$.id", is(1)))
//    				.andExpect(jsonPath("$.status", is("TEMPBLOCK")));
//    	
//    }
    

    /*
    ******************************
     */

    private long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split("/");
        return Long.valueOf(parts[parts.length - 1]);
    }


    private Seat mockHotel(String prefix) {
        Seat r = new Seat();
        return r;
    }
    
    private List<Seat> mockHotels(int size){
    	List<Seat> seats = new ArrayList<Seat>();
    	
    	for (int i=0; i < size; i++){
    		seats.add(new Seat());
    	}
    	return seats;
    }

    private byte[] toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r).getBytes();
    }

    // match redirect header URL (aka Location header)
    private static ResultMatcher redirectedUrlPattern(final String expectedUrlPattern) {
        return new ResultMatcher() {
            public void match(MvcResult result) {
                Pattern pattern = Pattern.compile("\\A" + expectedUrlPattern + "\\z");
                assertTrue(pattern.matcher(result.getResponse().getRedirectedUrl()).find());
            }
        };
    }

}

package com.ahmed.booking.domain;

import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

import org.apache.commons.lang.time.DateUtils;

/*
 * a simple domain entity doubling as a DTO
 */
@Entity
@Table(name = "movieSeats")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Seat {

    @Id
    @GeneratedValue()
    private long id;


    @Column(nullable = false,name="status")
    @Enumerated(EnumType.STRING)
    private SeatStatus status;
    
    private Date reservedTime;

    @Column(name="code")
    private String code;
    
    @Column(name="userName")
    private String userName;
    
    public Seat() {
    	status = SeatStatus.AVAILABLE;
    }


    public long getId() {
        return this.id;
    }

    // for tests ONLY
    public void setId(long id) {
        this.id = id;
    }

    

    public SeatStatus getStatus() {
		return status;
	}


	public void setStatus(SeatStatus status) {
		this.status = status;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	


	public Date getReservedtime() {
		return reservedTime;
	}


	public void setReservedtime(Date reservedTime) {
		this.reservedTime = reservedTime;
	}


	@Override
    public String toString() {
        return "Seat {" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", user='" + userName + '\'' +
                ", resTime='" + reservedTime + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

package com.ahmed.booking.dao.jpa;

import java.util.List;

import com.ahmed.booking.domain.Seat;
import com.ahmed.booking.domain.SeatStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository can be used to delegate CRUD operations
 */
public interface SeatRepository extends  CrudRepository<Seat, Long>{
    List<Seat> findSeatByStatus(SeatStatus status);
    Seat findSeatByCode(String code);

}

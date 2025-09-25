package com.wecp.progressive.controller;

import com.wecp.progressive.entity.TicketBooking;
import com.wecp.progressive.service.impl.TicketBookingServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketBookingController {

    @Autowired
    private TicketBookingServiceImpl ticketBookingServiceImpl;

    @GetMapping
    public ResponseEntity<List<TicketBooking>> getAllBookings() {
        return new ResponseEntity<>(ticketBookingServiceImpl.getAllTicketBookings(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> createBooking(@RequestBody TicketBooking ticketBooking) {
        return new ResponseEntity<>(ticketBookingServiceImpl.createBooking(ticketBooking),HttpStatus.CREATED);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> cancelBooking(@PathVariable int bookingId) {
        ticketBookingServiceImpl.cancelBooking(bookingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<TicketBooking>> getBookingsByUserEmail(@PathVariable String email) {
        return new ResponseEntity<>(ticketBookingServiceImpl.getBookingsByUserEmail(email),HttpStatus.OK);
    }
}
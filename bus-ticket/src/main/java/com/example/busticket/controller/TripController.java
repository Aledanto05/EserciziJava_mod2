package com.example.busticket.controller;

import com.example.busticket.dto.CreateTripDto;
import com.example.busticket.dto.PurchaseReceiptDto;
import com.example.busticket.entity.Trip;
import com.example.busticket.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    // Pubblico
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips(
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String destination
    ) {
        return ResponseEntity.ok(tripService.getAllTripsFiltered(origin, destination));
    }

    // Solo ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Trip> createTrip(@Valid @RequestBody CreateTripDto dto) {
        Trip trip = tripService.createTrip(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(trip);
    }

    // Autenticato
    @PostMapping("/{tripId}/buy")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<PurchaseReceiptDto> buyTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(tripService.buyTripForCurrentUser(tripId));
    }
}

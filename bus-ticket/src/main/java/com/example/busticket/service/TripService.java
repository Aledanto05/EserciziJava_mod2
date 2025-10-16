package com.example.busticket.service;

import com.example.busticket.dto.CreateTripDto;
import com.example.busticket.dto.PurchaseReceiptDto;
import com.example.busticket.entity.Trip;
import com.example.busticket.entity.User;
import com.example.busticket.exception.InsufficientCreditException;
import com.example.busticket.repository.TripRepository;
import com.example.busticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Trip> getAllTripsFiltered(String origin, String destination) {
        // Per semplicità: se presenti, filtra lato memoria; in produzione usare query nel repository
        List<Trip> all = tripRepository.findAll();
        return all.stream()
                .filter(t -> origin == null || t.getOrigin().equalsIgnoreCase(origin))
                .filter(t -> destination == null || t.getDestination().equalsIgnoreCase(destination))
                .toList();
    }

    public Trip createTrip(CreateTripDto dto) {
        if (dto.getPrice() == null || dto.getPrice().signum() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prezzo non valido");
        }
        if (dto.getOrigin() == null || dto.getOrigin().isBlank()
                || dto.getDestination() == null || dto.getDestination().isBlank()
                || dto.getDepartureTime() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campi obbligatori mancanti");
        }
        Trip t = new Trip();
        t.setOrigin(dto.getOrigin());
        t.setDestination(dto.getDestination());
        t.setDepartureTime(dto.getDepartureTime());
        t.setPrice(dto.getPrice());
        return tripRepository.save(t);
    }

    public PurchaseReceiptDto buyTripForCurrentUser(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip non trovato"));

        User user = getCurrentUserOrThrow();

        BigDecimal price = trip.getPrice();
        if (user.getCredit().compareTo(price) < 0) {
            throw new InsufficientCreditException("Credito insufficiente");
        }

        user.setCredit(user.getCredit().subtract(price));
        userRepository.save(user);

        PurchaseReceiptDto receipt = new PurchaseReceiptDto();
        receipt.setUserId(user.getId());
        receipt.setTripId(trip.getId());
        receipt.setCharge(price);
        receipt.setRemainingBalance(user.getCredit());
        return receipt;
    }

    private User getCurrentUserOrThrow() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated() || "anonymousUser".equals(auth.getPrincipal())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non autenticato");
        }
        String email = auth.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User non trovato"));
    }
}

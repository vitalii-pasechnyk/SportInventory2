package org.laba2.sportinventory2.controller;

import org.laba2.sportinventory2.entity.Rental;
import org.laba2.sportinventory2.entity.Rental.RentalStatus;
import org.laba2.sportinventory2.service.RentalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public ResponseEntity<Rental> createRental(@RequestBody Rental rental) {
        return ResponseEntity.ok(rentalService.createRental(rental));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getRentalById(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.getRentalById(id));
    }

    @GetMapping
    public ResponseEntity<List<Rental>> getAllRentals() {
        return ResponseEntity.ok(rentalService.getAllRentals());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Rental> updateRentalStatus(@PathVariable Long id, @RequestParam String status) {

        RentalStatus rentalStatus = RentalStatus.valueOf(status.toUpperCase());
        return ResponseEntity.ok(rentalService.updateRentalStatus(id, rentalStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return ResponseEntity.noContent().build();
    }
}

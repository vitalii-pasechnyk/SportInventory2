package org.laba2.sportinventory2.controller;

import org.laba2.sportinventory2.dto.BonusCardDTO;
import org.laba2.sportinventory2.service.BonusCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bonus-cards")
public class BonusCardController {

    private final BonusCardService bonusCardService;

    public BonusCardController(BonusCardService bonusCardService) {
        this.bonusCardService = bonusCardService;
    }

    @PostMapping
    public ResponseEntity<BonusCardDTO> createBonusCard(@RequestBody BonusCardDTO bonusCardDTO) {
        BonusCardDTO createdBonusCard = bonusCardService.createBonusCard(bonusCardDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBonusCard);}

    @GetMapping("/{id}")
    public ResponseEntity<BonusCardDTO> getBonusCardById(@PathVariable Long id) {
        BonusCardDTO bonusCard = bonusCardService.getBonusCardById(id);
        return ResponseEntity.ok(bonusCard);
    }

    @GetMapping
    public ResponseEntity<List<BonusCardDTO>> getAllBonusCards() {
        List<BonusCardDTO> bonusCards = bonusCardService.getAllBonusCards();
        return ResponseEntity.ok(bonusCards);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BonusCardDTO> updateBonusCard(@PathVariable Long id, @RequestBody BonusCardDTO updatedBonusCardDTO) {
        BonusCardDTO updatedBonusCard = bonusCardService.updateBonusCard(id, updatedBonusCardDTO);
        return ResponseEntity.ok(updatedBonusCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonusCard(@PathVariable Long id) {
        bonusCardService.deleteBonusCard(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/add-bonus")
    public ResponseEntity<Void> addBonus(@PathVariable Long id, @RequestParam double rentalAmount) {
        bonusCardService.addBonus(id, rentalAmount);
        return ResponseEntity.ok().build(); }
}

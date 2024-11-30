package org.laba2.sportinventory2.controller;

import jakarta.validation.Valid;
import org.laba2.sportinventory2.dto.EquipmentDTO;
import org.laba2.sportinventory2.entity.Equipment;
import org.laba2.sportinventory2.service.EquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    // POST - Create new equipment
    @PostMapping
    public ResponseEntity<Equipment> addEquipment(@Valid @RequestBody EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setName(equipmentDTO.getName());


        try {
            equipment.setCategory(equipmentDTO.getCategoryEnum());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

        equipment.setRentalPrice(equipmentDTO.getRentalPrice());
        equipment.setQuantity(equipmentDTO.getQuantity());
        return ResponseEntity.ok(equipmentService.addEquipment(equipment));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getEquipmentById(@PathVariable Long id) {
        return ResponseEntity.ok(equipmentService.getEquipmentById(id));
    }


    @GetMapping
    public ResponseEntity<List<Equipment>> getAllEquipment() {
        return ResponseEntity.ok(equipmentService.getAllEquipment());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @Valid @RequestBody EquipmentDTO updatedEquipmentDTO) {
        Equipment updatedEquipment = new Equipment();
        updatedEquipment.setId(id);
        updatedEquipment.setName(updatedEquipmentDTO.getName());

        try {
            updatedEquipment.setCategory(updatedEquipmentDTO.getCategoryEnum());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }

        updatedEquipment.setRentalPrice(updatedEquipmentDTO.getRentalPrice());
        updatedEquipment.setQuantity(updatedEquipmentDTO.getQuantity());
        return ResponseEntity.ok(equipmentService.updateEquipment(id, updatedEquipment));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}

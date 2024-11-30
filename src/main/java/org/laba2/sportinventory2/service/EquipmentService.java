package org.laba2.sportinventory2.service;

import org.laba2.sportinventory2.entity.Equipment;
import org.laba2.sportinventory2.repository.EquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }


    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }


    public Equipment getEquipmentById(Long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        return equipment.orElseThrow(() -> new RuntimeException("Equipment not found"));
    }


    public List<Equipment> getAllEquipment() {
        return equipmentRepository.findAll();
    }


    public Equipment updateEquipment(Long id, Equipment updatedEquipment) {
        if (!equipmentRepository.existsById(id)) {
            throw new RuntimeException("Equipment not found");
        }
        updatedEquipment.setId(id);
        return equipmentRepository.save(updatedEquipment);
    }


    public void deleteEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) {
            throw new RuntimeException("Equipment not found");
        }
        equipmentRepository.deleteById(id);
    }
}

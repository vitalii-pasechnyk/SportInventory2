package org.laba2.sportinventory2.repository;


import org.laba2.sportinventory2.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}

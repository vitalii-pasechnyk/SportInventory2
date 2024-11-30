package org.laba2.sportinventory2.repository;

import org.laba2.sportinventory2.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface RentalRepository extends JpaRepository<Rental, Long> {

    @Query("SELECT COUNT(r) > 0 FROM Rental r WHERE r.equipmentId = :equipmentId AND " +
            "(:startDate BETWEEN r.startDate AND r.endDate OR :endDate BETWEEN r.startDate AND r.endDate OR " +
            "(r.startDate BETWEEN :startDate AND :endDate))")
    boolean isEquipmentBooked(@Param("equipmentId") Long equipmentId,
                              @Param("startDate") LocalDate startDate,
                              @Param("endDate") LocalDate endDate);
}

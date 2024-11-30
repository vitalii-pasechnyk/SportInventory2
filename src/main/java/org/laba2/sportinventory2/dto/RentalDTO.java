package org.laba2.sportinventory2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDTO {
    private Long id;
    private Long equipmentId;
    private Long userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}

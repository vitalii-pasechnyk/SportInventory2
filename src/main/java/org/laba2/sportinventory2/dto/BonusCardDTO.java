package org.laba2.sportinventory2.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonusCardDTO {
    private Long id;
    private String cardNumber;
    private Double balance;
}

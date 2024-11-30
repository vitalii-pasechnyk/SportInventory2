package org.laba2.sportinventory2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BonusCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private Double balance = 0.0;

    @OneToOne(mappedBy = "bonusCard")
    private User user;

    public void addBonus(double amount) {
        double bonus = amount * 0.10;
        this.balance += bonus;
    }
}

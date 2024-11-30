package org.laba2.sportinventory2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Column(unique = true, nullable = false)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @OneToOne
    @JoinColumn(name = "bonus_card_id")
    private BonusCard bonusCard;

    @Column(nullable = false)
    private int rentalCount = 0;

    @Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;


    public void addBonus(BigDecimal bonus) {
        this.balance = this.balance.add(bonus);
        if (bonusCard != null) {
            bonusCard.addBonus(bonus.doubleValue());
        }
    }


    public void incrementRentalCount() {
        this.rentalCount++;
    }

}

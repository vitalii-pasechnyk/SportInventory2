package org.laba2.sportinventory2.repository;

import org.laba2.sportinventory2.entity.BonusCard;
import org.laba2.sportinventory2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonusCardRepository extends JpaRepository<BonusCard, Long> {
    BonusCard findByUser(User user);
}

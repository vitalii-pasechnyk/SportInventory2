package org.laba2.sportinventory2.repository;

import org.laba2.sportinventory2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}


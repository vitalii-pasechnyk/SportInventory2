package org.laba2.sportinventory2.service;

import org.laba2.sportinventory2.entity.Equipment;
import org.laba2.sportinventory2.entity.Rental;
import org.laba2.sportinventory2.entity.User;
import org.laba2.sportinventory2.repository.RentalRepository;
import org.laba2.sportinventory2.repository.UserRepository;
import org.laba2.sportinventory2.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmailService emailService;


    public RentalService(RentalRepository rentalRepository, UserRepository userRepository, EmailService emailService) {
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }


    public Rental createRental(Rental rental) {

        boolean isBooked = rentalRepository.isEquipmentBooked(
                rental.getEquipmentId(),
                rental.getStartDate(),
                rental.getEndDate()
        );

        if (isBooked) {
            throw new IllegalArgumentException("Equipment is already booked for the selected period");
        }


        Rental savedRental = rentalRepository.save(rental);


        User user = userRepository.findById(rental.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + rental.getUserId()));

        BigDecimal totalAmount = calculateRentalAmount(rental);
        BigDecimal bonus = totalAmount.multiply(BigDecimal.valueOf(0.1));


        user.addBonus(bonus);
        user.incrementRentalCount();


        userRepository.save(user);


        sendRentalConfirmationEmail(user, rental, totalAmount);

        return savedRental;
    }


    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental not found with id: " + id));
    }


    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }


    public Rental updateRentalStatus(Long id, Rental.RentalStatus status) {
        Rental rental = getRentalById(id);
        rental.setStatus(status);
        return rentalRepository.save(rental);
    }


    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }


    private BigDecimal calculateRentalAmount(Rental rental) {
        long days = rental.getStartDate().until(rental.getEndDate()).getDays();
        BigDecimal dailyRate = BigDecimal.valueOf(100);
        return dailyRate.multiply(BigDecimal.valueOf(days));
    }

    private void sendRentalConfirmationEmail(User user, Rental rental, BigDecimal totalAmount) {

        Equipment equipment = equipmentRepository.findById(rental.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment not found with id: " + rental.getEquipmentId()));

        String subject = "Підтвердження бронювання спорядження";
        String content = String.format(
                "Шановний %s,\n" +
                        "Дякуємо за ваше бронювання спорядження!\n" +
                        "Ось деталі вашого бронювання:\n" +
                        "\n" +
                        "Назва спорядження: %s\n" +
                        "Дата початку: %s\n" +
                        "Дата завершення: %s\n" +
                        "Загальна сума: %.2f грн\n" +
                        "\n" +
                        "Бажаємо вам приємного користування нашим спорядженням!\n" +
                        "З найкращими побажаннями,\n" +
                        "Команда SportInventory",
                user.getFirstName(),
                equipment.getName(),
                rental.getStartDate(),
                rental.getEndDate(),
                totalAmount
        );

        emailService.sendEmail(user.getEmail(), subject, content);
    }

}
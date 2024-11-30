package org.laba2.sportinventory2.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Category cannot be null")
    private Category category;

    @Min(value = 0, message = "Rental price must be a positive number")
    private Double rentalPrice;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull(message = "Name cannot be null") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name cannot be null") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String name) {
        this.name = name;
    }

    public @NotNull(message = "Category cannot be null") Category getCategory() {
        return category;
    }

    public void setCategory(@NotNull(message = "Category cannot be null") Category category) {
        this.category = category;
    }

    public @Min(value = 0, message = "Rental price must be a positive number") Double getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(@Min(value = 0, message = "Rental price must be a positive number") Double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public @Min(value = 1, message = "Quantity must be at least 1") Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 1, message = "Quantity must be at least 1") Integer quantity) {
        this.quantity = quantity;
    }

}
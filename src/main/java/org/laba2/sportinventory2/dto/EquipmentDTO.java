package org.laba2.sportinventory2.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.laba2.sportinventory2.entity.Category;

public class EquipmentDTO {

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    @NotNull(message = "Category cannot be null")
    private String category;

    @Min(value = 0, message = "Rental price must be a positive number")
    private Double rentalPrice;

    @Min(value = 1, message = "Quantity must be at least 1")
    private Integer quantity;

    public Category getCategoryEnum() {
        if (this.category == null || this.category.isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }


        try {
            return Category.valueOf(this.category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category value: '" + this.category + "'. Valid values are BEGINNER, AMATEUR, PROFESSIONAL.");
        }
    }

    public @NotNull(message = "Name cannot be null") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String getName() {
        return name;
    }

    public void setName(@NotNull(message = "Name cannot be null") @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters") String name) {
        this.name = name;
    }

    public void setCategory(@NotNull(message = "Category cannot be null") String category) {
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

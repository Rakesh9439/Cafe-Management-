package com.cafeManagement.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemDto {



    // ✅ No-args constructor (needed by Jackson)
    public MenuItemDto() {}

    // ✅ All-args constructor
    public MenuItemDto(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    private Long id;

    private String name;

    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

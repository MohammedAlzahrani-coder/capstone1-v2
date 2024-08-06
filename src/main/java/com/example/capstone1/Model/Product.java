package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Product {


    @NotNull(message = "The id should not be null")
    private int id;

    @NotEmpty(message = "The name should not be empty")
    private String name;

    @Positive
    @NotNull(message = "The price should not be null")
    private int price;


    @NotNull(message = "The category id should not be null")
    private int categoryId;


    private int sale;

    @NotNull(message = "The stocks should not be null")
    private int stocks;

}

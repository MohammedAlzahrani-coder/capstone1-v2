package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Category {



    @NotNull(message =  "The id should not be null")
    private int id;

    @NotEmpty(message = "The name should not be empty")
    private String name;



}

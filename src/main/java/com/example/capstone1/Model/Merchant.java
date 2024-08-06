package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
public class Merchant {


    @NotNull(message = "The id should not be null")
    private int id;


    @Length(min = 4, max = 50, message = "The name should be between 3 and 50 characters")
    @NotEmpty(message = "The name should not be empty")
    private String name;



    private int sales;

}

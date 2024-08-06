package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
public class User {



    @NotNull(message = "The id should not be null")
    private int id;

    @Length(min = 6)
    @NotEmpty(message = "The username should not be empty")
    private String user_name;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d).{6,}$", message = "Password must contain at least one letter and one digit, and be at least 6 characters long")
    @NotEmpty(message = "The password should not be empty")
    private String password;


    @Email(message = "The email should be on email format")
    @NotEmpty(message = "The email should not be empty")
    private String email;


    @NotEmpty(message = "The role should not be empty")
    @Pattern(regexp = "^(Admin|Customer)$", message = "Role must be either 'Admin' or 'Customer'")
    private String role;


    @Positive
    @NotNull(message = "The balance should not be null")
    private int balance;

}

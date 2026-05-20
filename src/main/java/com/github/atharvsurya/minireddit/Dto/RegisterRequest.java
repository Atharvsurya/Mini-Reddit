package com.github.atharvsurya.minireddit.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String confirmpassword;
    private String username;
    private LocalDate dob;
}

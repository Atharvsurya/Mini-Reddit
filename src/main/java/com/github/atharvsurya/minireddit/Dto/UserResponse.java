package com.github.atharvsurya.minireddit.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long user_id;
    private String username;
    private String email;
}

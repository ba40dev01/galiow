package com.badev.mynote.dto;

import lombok.Data;

@Data

public class RegisterRequest {
    private String phone;
    private String email;
    private String name;
    private String username;
    private String password;


}


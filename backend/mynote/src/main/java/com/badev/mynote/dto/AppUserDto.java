package com.badev.mynote.dto;

import com.badev.mynote.entity.AppUser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDto {
    public String phoneNumber;
    public String password;
    public String fullName;
    public String email;

    private static AppUserDto map(AppUser appUser){
        return AppUserDto.builder()
                .fullName(appUser.getFullName())
                .phoneNumber(appUser.getPhoneNumber())

                .email(appUser.getEmail())
                .build();
    }

    public static AppUserDto from(AppUser appUser){
        return map(appUser);
    }
}

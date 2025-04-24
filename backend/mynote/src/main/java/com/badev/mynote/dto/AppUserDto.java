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
    public Long roleId;
    public String roleName;

    private static AppUserDto map(AppUser appUser){
        return AppUserDto.builder()
                .fullName(appUser.getFullName())
                .phoneNumber(appUser.getPhoneNumber())
                .roleName(appUser.getRole().getName())
                .roleId(appUser.getRole().getId())
                .email(appUser.getEmail())
                .build();
    }

    public static AppUserDto from(AppUser appUser){
        return map(appUser);
    }
}

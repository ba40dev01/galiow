package com.badev.mynote.service.AppUser;

import com.badev.mynote.dto.AppUserDto;
import com.badev.mynote.entity.AppUser.AppUser;
import com.badev.mynote.repository.appUser.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository repository;
    private final PasswordEncoder encoder;


    public AppUser save(AppUserDto dto){
        Optional<AppUser> check = repository.findByUsername(dto.phoneNumber);
        if(check !=null){
            throw new RuntimeException("this phone number is already used");
        }
        AppUser user = new AppUser();
        user.setFullName(dto.fullName);
        user.setPassword(encoder.encode(dto.password));
        user.setPhoneNumber(dto.phoneNumber);
        user.setUsername(dto.phoneNumber);


        return user;
    }

    public List<AppUserDto> getUser(){
        return repository.findAll().stream().map(AppUserDto::from)
                .toList();

    }


}

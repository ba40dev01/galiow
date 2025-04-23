package com.badev.mynote.service.AppUser;

import com.badev.mynote.entity.AppUser.AppRole;
import com.badev.mynote.repository.AppUser.AppRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppRoleService {

    private final AppRoleRepository appRoleRepository;

    public String save(String roleName){
        AppRole appRole = new AppRole();
        appRole.setName(roleName);

        appRoleRepository.save(appRole);
        return appRole.getName();
    }

    public List<String> getRoles(){

        List<String> roles =new ArrayList<>();
        appRoleRepository.findAll().forEach(appRole -> roles.add(appRole.getName()));
        return roles;
    }
}

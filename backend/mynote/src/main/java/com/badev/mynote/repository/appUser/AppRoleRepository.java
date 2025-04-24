package com.badev.mynote.repository.appUser;

;
import com.badev.mynote.entity.AppUser.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    Optional<AppRole> findByName(String username);

}

package com.badev.mynote.entity.AppUser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppRole {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<AppUser> appUser;

    public AppRole(String name) {
        this.name = name;
    }
}

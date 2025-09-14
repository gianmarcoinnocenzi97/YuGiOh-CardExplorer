package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "rarity")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Rarity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    @OneToMany(mappedBy = "rarity")
    @JsonIgnore
    private List<CardRelease> cardReleases;
}

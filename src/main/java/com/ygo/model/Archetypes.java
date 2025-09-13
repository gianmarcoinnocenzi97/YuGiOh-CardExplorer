package com.ygo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "archetypes")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Archetypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}

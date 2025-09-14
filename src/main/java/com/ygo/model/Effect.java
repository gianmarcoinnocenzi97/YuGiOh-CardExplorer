package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "effect")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Effect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String condition;
    private String cost;
    private String resolution;
    private String explanation;
    @ManyToMany(mappedBy = "effects")
    @JsonIgnore
    private List<Card> cards;

}

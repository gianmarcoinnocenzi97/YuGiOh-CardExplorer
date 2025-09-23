package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "effect_tag")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EffectTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "effectTags")
    @JsonIgnore
    private List<Card> cards;
}

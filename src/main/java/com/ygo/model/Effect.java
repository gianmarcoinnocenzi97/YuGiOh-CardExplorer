package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Table(name = "effect")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Effect {

    @Id
    @UuidGenerator
    private String id;
    @Column(name = "effect_condition")  // evita "condition"
    private String condition;
    private String cost;
    private String resolution;
    private String explanation;
    @ManyToMany(mappedBy = "effects")
    @JsonIgnore
    private List<Card> cards;

}

package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Table(name = "effect_tag")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EffectTag {

    @Id
    @UuidGenerator
    private String id;
    private String name;
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore
    private List<Card> cards;
}

package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "card")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "desc")
    private String description;
    private Double atk;
    private Double def;
    private Integer level;
    private Integer rank;
    private Integer linkVal;
    @Column(columnDefinition = "json")
    private String linkmarkers;
    @Column(columnDefinition = "json")
    private String analyzedDesc;
    @ManyToOne
    @JoinColumn(name = "attribute_id", referencedColumnName = "id")
    private Attribute attribute;
    @ManyToOne
    @JoinColumn(name = "race_id", referencedColumnName = "id")
    private Race race;
    @ManyToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;
    @ManyToOne
    @JoinColumn(name = "archetype_id", referencedColumnName = "id")
    private Archetype archetype;
    @ManyToOne
    @JoinColumn(name = "frame_type_id", referencedColumnName = "id")
    private FrameType frameType;
    @ManyToMany
    @JoinTable(
            name = "card_has_effect_tag",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "effect_tag_id")
    )
    private List<EffectTag> tags;
    @ManyToMany
    @JoinTable(
            name = "card_effect",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "effect_id")
    )
    private List<Effect> effects;
    @OneToMany(mappedBy = "card")
    @JsonIgnore
    private List<CardRelease> cardReleases;
    @OneToOne(mappedBy = "card")
    private Price price;


}

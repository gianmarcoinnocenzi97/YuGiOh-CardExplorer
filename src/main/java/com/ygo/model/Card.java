package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "card")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Card {

    @Id
    private String id;
    private String name;
    @Column(columnDefinition = "LONGTEXT")
    private String description;
    private Integer atk;
    private Integer def;
    private Integer level;
    @Column(name = "card_rank")
    private Integer rank;
    private Integer linkVal;
    @Column(columnDefinition = "LONGTEXT")
    private String linkmarkers;
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
    private Set<EffectTag> effectTags = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "card_effect",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "effect_id")
    )
    private List<Effect> effects = new ArrayList<>();
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CardRelease> cardReleases = new ArrayList<>();
    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private Price price;
}

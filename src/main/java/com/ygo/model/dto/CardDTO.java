package com.ygo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardDTO {

    private String id;
    private String name;
    private String description;
    private Double atk;
    private Double def;
    private Integer level;
    private Integer rank;
    private Integer linkVal;
    private String linkmarkers;
    private AttributeDTO attribute;
    private RaceDTO race;
    private TypeDTO type;
    private ArchetypeDTO archetype;
    private FrameTypeDTO frameType;
    private List<EffectTagDTO> tags;
    private List<EffectDTO> effects;
    private List<CardReleaseDTO> cardReleases;
    private PriceDTO price;


}

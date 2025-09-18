package com.ygo.model.critiria;

import lombok.Data;

import java.util.List;

@Data
public class CardCriteria {
    private List<Long> ids;
    private String name;
    private String type;
    private String race;
    private List<String> archetypes;
    private List<String> attributes;
    private List<String> frameTypes;
    private Integer atkMin;
    private Integer defMin;
    private Integer level;
    private Integer levelMin;
    private Integer levelMax;
    private Integer rank;
    private Integer rankMin;
    private Integer rankMax;
    private Integer linkval;
    private Integer linkvalMin;
    private Integer linkvalMax;
    private List<String> effCats;
    private String effCatOp; // "any" o "all"
}

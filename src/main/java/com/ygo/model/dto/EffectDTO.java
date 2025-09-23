package com.ygo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EffectDTO {

    private Long id;
    private String condition;
    private String cost;
    private String resolution;
    private String explanation;


}

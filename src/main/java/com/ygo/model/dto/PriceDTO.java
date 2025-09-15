package com.ygo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PriceDTO {

    private Long id;
    private Double cardmarket;
    private Double tcgplayer;
    private Double ebay;
    private Double amazon;
    private Double coolstuffinc;


}

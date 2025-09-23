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
    private String cardmarket;
    private String tcgplayer;
    private String ebay;
    private String amazon;
    private String coolstuffinc;


}

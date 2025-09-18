package com.ygo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "price")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double cardmarket;
    private Double tcgplayer;
    private Double ebay;
    private Double amazon;
    private Double coolstuffinc;
    @OneToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;


}

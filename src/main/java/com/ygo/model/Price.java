package com.ygo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "price")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Id
    @UuidGenerator
    private String id;
    private String cardmarket;
    private String tcgplayer;
    private String ebay;
    private String amazon;
    private String coolstuffinc;
    @OneToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;


}

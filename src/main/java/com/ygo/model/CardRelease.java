package com.ygo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Table(name = "card_release")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CardRelease {

    @Id
    @UuidGenerator
    private String id;
    private String price;
    @ManyToOne
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;
    @ManyToOne
    @JoinColumn(name = "set_id", referencedColumnName = "id")
    private Set setCard;
    @ManyToOne
    @JoinColumn(name = "rarity_id", referencedColumnName = "id")
    private Rarity rarityCard;
    
}

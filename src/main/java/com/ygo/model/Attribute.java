package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Table(name = "attribute")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Attribute {

    @Id
    @UuidGenerator
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "attribute")
    @JsonIgnore
    private List<Card> cards;

    
}

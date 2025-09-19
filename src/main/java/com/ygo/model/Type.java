package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Table(name = "type")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Type {

    @Id
    @UuidGenerator
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "type")
    @JsonIgnore
    private List<Card> cards;

    
}

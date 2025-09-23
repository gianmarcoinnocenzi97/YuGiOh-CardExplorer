package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Table(name = "frame_type")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FrameType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "frameType")
    @JsonIgnore
    private List<Card> cards;

}

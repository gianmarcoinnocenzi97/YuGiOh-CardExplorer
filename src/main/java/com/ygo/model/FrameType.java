package com.ygo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;

@Table(name = "frame_type")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FrameType {

    @Id
    @UuidGenerator
    private String id;
    private String name;
    @OneToMany(mappedBy = "frameType")
    @JsonIgnore
    private List<Card> cards;

}

package com.ygo.model.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @Id
    @UuidGenerator
    private String id;
    private String description;
    private String code;

}

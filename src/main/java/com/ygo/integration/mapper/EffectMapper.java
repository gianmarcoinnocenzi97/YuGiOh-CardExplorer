package com.ygo.integration.mapper;

import com.ygo.model.Effect;
import com.ygo.model.dto.EffectDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EffectMapper {

    Effect dtoToEntity(EffectDTO effectDTO);
    EffectDTO entityToDto(Effect effect);

    List<Effect> dtoToEntityList(List<EffectDTO> effectDTOS);
    List<EffectDTO> entityToDtoList(List<Effect> effects);

}


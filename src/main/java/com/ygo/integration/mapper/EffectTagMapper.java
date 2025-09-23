package com.ygo.integration.mapper;

import com.ygo.model.EffectTag;
import com.ygo.model.dto.EffectTagDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EffectTagMapper {

    EffectTag dtoToEntity(EffectTagDTO effectTagDTO);
    EffectTagDTO entityToDto(EffectTag effectTag);

    List<EffectTag> dtoToEntityList(List<EffectTagDTO> effectTagDTOS);
    List<EffectTagDTO> entityToDtoList(List<EffectTag> effectTags);

}


package com.ygo.integration.mapper;

import com.ygo.model.Card;
import com.ygo.model.dto.CardDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {

    Card dtoToEntity(CardDTO cardDTO);
    CardDTO entityToDto(Card card);

    List<Card> dtoToEntityList(List<CardDTO> cardsDTO);
    List<CardDTO> entityToDtoList(List<Card> cards);

}


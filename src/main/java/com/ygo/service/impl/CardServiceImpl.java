package com.ygo.service.impl;

import com.ygo.integration.ResourceNotFoundException;
import com.ygo.integration.mapper.CardMapper;
import com.ygo.integration.specification.CardSpecification;
import com.ygo.model.Card;
import com.ygo.model.critiria.CardCriteria;
import com.ygo.model.dto.CardDTO;
import com.ygo.repository.CardRepository;
import com.ygo.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardSpecification cardSpecification;
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;

    @Override
    public CardDTO getCardById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Card with id " + id + " not found"));
        return cardMapper.entityToDto(card);
    }

    @Override
    public Page<CardDTO> cardFilter(CardCriteria criteria, Pageable pageable) {
        Specification<Card> filterSpecification = cardSpecification.getSpecification(criteria);
        Page<Card> page = cardRepository.findAll(filterSpecification, pageable);
        return page.map(cardMapper::entityToDto);
    }

}




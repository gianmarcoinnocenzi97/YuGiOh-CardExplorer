package com.ygo.service;

import com.ygo.model.critiria.CardCriteria;
import com.ygo.model.dto.CardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CardService {

    CardDTO getCardById(Long id);
    Page<CardDTO> cardFilter(CardCriteria criteria, Pageable pageable);
}

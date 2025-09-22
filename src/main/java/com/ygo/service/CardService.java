package com.ygo.service;

import com.ygo.model.critiria.CardCriteria;
import com.ygo.model.dto.CardDTO;
import com.ygo.model.dto.request.CardIdsPdfRequest;
import com.ygo.model.pojo.CardContainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface CardService {

    CardDTO getCardById(Long id);
    Page<CardDTO> cardFilter(CardCriteria criteria, Pageable pageable);
    List<CardDTO> findByNames(List<String> names);
    CardContainer fetchAllCards();
    byte[] exportToPdf(CardIdsPdfRequest request) throws IOException;
    void updateCards();
}

package com.ygo.service.impl;

import com.ygo.common.PdfUtils;
import com.ygo.integration.ResourceNotFoundException;
import com.ygo.integration.YgoProDeckClient;
import com.ygo.integration.mapper.CardMapper;
import com.ygo.integration.specification.CardSpecification;
import com.ygo.model.Card;
import com.ygo.model.critiria.CardCriteria;
import com.ygo.model.dto.CardDTO;
import com.ygo.model.dto.request.CardIdsPdfRequest;
import com.ygo.model.pojo.CardContainer;
import com.ygo.repository.CardRepository;
import com.ygo.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardSpecification cardSpecification;
    private final CardRepository cardRepository;
    private final CardMapper cardMapper;
    private final PdfUtils pdfUtils;
    private final YgoProDeckClient client;

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

    @Override
    public List<CardDTO> findByNames(List<String> names) {
        List<Card> cards = cardRepository.findByNameInIgnoreCase(names);
        return cards.stream().map(cardMapper::entityToDto).toList();
    }

    @Override
    public Mono<CardContainer> fetchAllCards() {
          return client.fetchAllCardsMono()
                .onErrorResume(e -> Mono.error(new RuntimeException("Errore nel recupero delle carte", e)));
    }

    @Override
    public byte[] exportToPdf(CardIdsPdfRequest request) throws IOException {
        List<Card> cards = cardRepository.findAllById(
                request.ids().stream().map(Long::valueOf).toList()
        );

        List<CardDTO> cardDTOs = cards.stream()
                .map(cardMapper::entityToDto)
                .toList();

        return pdfUtils.generatePdf(cardDTOs);
    }


}

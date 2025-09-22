package com.ygo.service.impl;

import com.ygo.common.PdfUtils;
import com.ygo.integration.ResourceNotFoundException;
import com.ygo.integration.YgoProDeckClient;
import com.ygo.integration.mapper.CardMapper;
import com.ygo.integration.specification.CardSpecification;
import com.ygo.model.*;
import com.ygo.model.critiria.CardCriteria;
import com.ygo.model.dto.CardDTO;
import com.ygo.model.pojo.CardContainer;
import com.ygo.model.pojo.CardPricesItem;
import com.ygo.model.pojo.CardSetsItem;
import com.ygo.model.pojo.DataItem;
import com.ygo.repository.*;
import com.ygo.service.CardService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;
    private final RaceRepository raceRepository;
    private final TypeRepository typeRepository;
    private final ArchetypeRepository archetypeRepository;
    private final AttributeRepository attributeRepository;
    private final FrameTypeRepository frameTypeRepository;
    private final RarityRepository rarityRepository;
    private final SetRepository setRepository;

    private final CardSpecification cardSpecification;
    private final CardMapper cardMapper;
    private final PdfUtils pdfUtils;
    private final YgoProDeckClient client;

    private final ExecutorService executor = Executors.newFixedThreadPool(25);

    @Override
    public CardDTO getCardById(String id) {
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
    public CardContainer fetchAllCards() {
        try {
            return client.fetchAllCardsMono()
                    .blockOptional()
                    .orElseThrow(() -> new RuntimeException("Nessuna carta trovata"));
        } catch (Exception e) {
            throw new RuntimeException("Errore nel recupero delle carte", e);
        }
    }

    @Override
    public java.util.Set<String> getIdWithoutEffectOrCat() {
        return cardRepository.findCardsWithoutEffectsAndTagsExcludingNormalTokenSkill();
    }



    @Override
    public byte[] exportToPdf(List<String> ids) throws IOException {
        List<Card> cards = cardRepository.findAllById(ids);

        List<CardDTO> cardDTOs = cards.stream()
                .map(cardMapper::entityToDto)
                .toList();

        return pdfUtils.generatePdf(cardDTOs);
    }

    @Transactional
    @Override
    public void updateCards() {
        CardContainer cardContainer = client.fetchAllCardsMono().block();

        if (cardContainer == null || cardContainer.getData() == null) {
            return;
        }

        List<DataItem> cards = cardContainer.getData();

        List<CompletableFuture<Void>> futures = cards.stream()
                .map(data -> CompletableFuture.runAsync(() -> processAndSaveCard(data), executor))
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private void processAndSaveCard(DataItem data) {
        Card card = createCard(data);
        processPrices(data, card);
        processReleases(data, card);
        cardRepository.save(card);
    }

    private Card createCard(DataItem data) {
        Card card = new Card();
        card.setId(String.valueOf(data.getId()));
        card.setName(data.getName());
        card.setDescription(data.getDesc());
        card.setAtk(data.getAtk());
        card.setDef(data.getDef());
        card.setLevel(data.getLevel());

        if (data.getRace() != null) card.setRace(raceRepository.findOrCreateByName(data.getRace()));
        if (data.getType() != null) card.setType(typeRepository.findOrCreateByName(data.getType()));
        if (data.getArchetype() != null) card.setArchetype(archetypeRepository.findOrCreateByName(data.getArchetype()));
        if (data.getAttribute() != null) card.setAttribute(attributeRepository.findOrCreateByName(data.getAttribute()));
        if (data.getFrameType() != null) card.setFrameType(frameTypeRepository.findOrCreateByName(data.getFrameType()));

        return card;
    }

    private void processPrices(DataItem data, Card card) {
        if (data.getCard_prices() != null && !data.getCard_prices().isEmpty()) {
            CardPricesItem priceItem = data.getCard_prices().get(0);
            Price price = new Price();
            price.setCard(card);
            price.setCardmarket(priceItem.getCardmarket_price());
            price.setEbay(priceItem.getEbay_price());
            price.setAmazon(priceItem.getAmazon_price());
            price.setTcgplayer(priceItem.getTcgplayer_price());
            price.setCoolstuffinc(priceItem.getCoolstuffinc_price());
            card.setPrice(price);
        }
    }

    private void processReleases(DataItem data, Card card) {
        if (data.getCard_sets() != null) {
            List<CardRelease> releases = new ArrayList<>();
            for (CardSetsItem setItem : data.getCard_sets()) {
                CardRelease release = new CardRelease();
                release.setCard(card);

                if (setItem.getSet_rarity_code() != null && setItem.getSet_rarity() != null) {
                    Rarity rarity = rarityRepository.findOrCreateByName(
                            setItem.getSet_rarity_code().replaceAll("[()]", ""),
                            setItem.getSet_rarity()
                    );
                    release.setRarityCard(rarity);
                }

                if (setItem.getSet_code() != null && setItem.getSet_name() != null) {
                    Set setEntity = setRepository.findOrCreateByName(
                            setItem.getSet_code(),
                            setItem.getSet_name()
                    );
                    release.setSetCard(setEntity);
                }

                release.setPrice(setItem.getSet_price());
                releases.add(release);
            }
            card.setCardReleases(releases);
        }
    }

}

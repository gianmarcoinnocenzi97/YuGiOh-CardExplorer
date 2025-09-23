package com.ygo.service.impl;

import com.ygo.integration.ResourceNotFoundException;
import com.ygo.integration.mapper.EffectMapper;
import com.ygo.model.Card;
import com.ygo.model.Effect;
import com.ygo.model.dto.request.InsertEffectRequest;
import com.ygo.repository.CardRepository;
import com.ygo.repository.EffectRepository;
import com.ygo.service.EffectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EffectServiceImpl implements EffectService {

    private final EffectRepository effectRepository;
    private final CardRepository cardRepository;

    private final EffectMapper effectMapper;

    @Override
    public void insertEffect(InsertEffectRequest insertEffectRequest) {

        Card card = cardRepository.findById(insertEffectRequest.idCard())
                .orElseThrow(() -> new ResourceNotFoundException("Card with id " + insertEffectRequest.idCard() + " not found"));

        List<Effect> effect = effectRepository.saveAll(effectMapper.dtoToEntityList(insertEffectRequest.effects()));
        card.setEffects(effect);
        cardRepository.save(card);

    }

}




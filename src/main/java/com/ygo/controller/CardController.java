package com.ygo.controller;


import com.ygo.common.AppConstants;
import com.ygo.model.dto.CardDTO;
import com.ygo.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(AppConstants.CARD_URL)
@RestController
public class CardController {

    private final CardService cardService;

    @Operation(description = "Retrieves full details for a single card, including its parsed effects")
    @GetMapping("/{cardId}")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long cardId) {
        CardDTO card = cardService.getCardById(cardId);
        return ResponseEntity.ok(card);
    }
}

package com.ygo.rest;


import com.ygo.common.AppConstants;
import com.ygo.model.critiria.CardCriteria;
import com.ygo.model.dto.CardDTO;
import com.ygo.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(description = "Filters cards based on multiple criteria and returns a paginated list of results.")
    @PostMapping("/filter")
    public ResponseEntity<Page<CardDTO>> searchCards(@RequestBody CardCriteria criteria, Pageable pageable) {
        return ResponseEntity.ok(cardService.cardFilter(criteria, pageable));
    }
}

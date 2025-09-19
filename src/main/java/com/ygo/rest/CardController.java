package com.ygo.rest;


import com.ygo.common.AppConstants;
import com.ygo.model.critiria.CardCriteria;
import com.ygo.model.dto.CardDTO;
import com.ygo.model.dto.request.CardIdsPdfRequest;
import com.ygo.model.dto.request.CardNameRequest;
import com.ygo.model.pojo.CardContainer;
import com.ygo.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

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

    @Operation(description = "Returns all cards matching the provided names, case-insensitive.")
    @PostMapping("/by_names")
    public ResponseEntity<List<CardDTO>> getCardsByNames(@RequestBody @Valid CardNameRequest request) {
        return ResponseEntity.ok(cardService.findByNames(request.names()));
    }

    @GetMapping("/cards")
    public Mono<CardContainer> getAllCards() {
        return cardService.fetchAllCards();
    }

    @GetMapping("/update")
    public void updateCards() {
        cardService.updateCards();
    }

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportToPdf(@RequestBody CardIdsPdfRequest request) {
        try {
            byte[] pdfBytes = cardService.exportToPdf(request);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"cards.pdf\"")
                    .body(pdfBytes);

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error generating PDF. Please try again.", e);
        }
    }
}

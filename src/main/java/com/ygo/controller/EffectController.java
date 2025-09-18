package com.ygo.controller;


import com.ygo.common.AppConstants;
import com.ygo.model.dto.request.EffectRequest;
import com.ygo.service.EffectService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping(AppConstants.EFFECT_URL)
@RestController
public class EffectController {

    private final EffectService effectService;

    @Operation(summary = "Create or link effects to a card",
            description = "Creates new effects or links existing ones to a card. Supports replacing all existing links.")
    @PostMapping("/{cardId}/add")
    public ResponseEntity<HttpStatus> createOrLinkEffects(@PathVariable Long cardId,
                                                          @RequestBody EffectRequest request) {
        return ResponseEntity.ok().body(null);
    }
}


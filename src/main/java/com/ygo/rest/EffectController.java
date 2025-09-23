package com.ygo.rest;


import com.ygo.common.AppConstants;
import com.ygo.model.dto.request.InsertEffectRequest;
import com.ygo.service.EffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(AppConstants.EFFECT_URL)
@RestController
public class EffectController {

    private final EffectService effectService;

    @PostMapping("/insertEffect")
    public ResponseEntity<HttpStatus> insertEffect(@RequestBody InsertEffectRequest insertEffectRequest) {
        effectService.insertEffect(insertEffectRequest);
        return ResponseEntity.ok().build();
    }
}


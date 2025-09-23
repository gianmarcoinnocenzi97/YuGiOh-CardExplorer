package com.ygo.model.dto.request;

import com.ygo.model.dto.EffectDTO;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record InsertEffectRequest(
        @NotBlank @Email String idCard,
        @NotBlank List<EffectDTO> effects
) {}

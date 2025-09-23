package com.ygo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record InsertEffectTagRequest(
        @NotBlank @Email String idCard,
        @NotBlank String effectTag
) {}

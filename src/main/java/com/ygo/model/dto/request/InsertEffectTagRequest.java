package com.ygo.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record InsertEffectTagRequest(
        @NotBlank @Email String idCard,
        @NotBlank List<String> effectTag
) {}

package com.ygo.model.dto.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CardIdsPdfRequest(
        @NotEmpty(message = "IDs list cannot be empty")
        List<String> ids
) {}
